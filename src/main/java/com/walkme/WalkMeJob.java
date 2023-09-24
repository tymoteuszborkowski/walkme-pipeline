package com.walkme;

import com.walkme.domain.MappedActivity;
import com.walkme.domain.avro.Activity;
import com.walkme.domain.avro.AggregatedActivities;
import com.walkme.domain.avro.SimplifiedActivity;
import com.walkme.services.AsyncTestEnvironmentsApiFunction;
import com.walkme.services.TestEnvironmentApiResponse;
import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.connector.file.sink.FileSink;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.core.fs.Path;
import org.apache.flink.formats.parquet.avro.AvroParquetReaders;
import org.apache.flink.formats.parquet.avro.AvroParquetWriters;
import org.apache.flink.streaming.api.datastream.AsyncDataStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class WalkMeJob {


    public static void main(String[] args) throws Exception {

        ParameterTool parameters = ParameterTool.fromArgs(args);
        String inputDataPath = parameters.get("inputDataPath");
        String outputDataPath = parameters.get("outputDataPath");
        String acceptedActivityTypesStr = parameters.get("acceptedActivityTypes");
        LocalDate inputFrom = LocalDate.parse(parameters.get("inputFrom"));
        LocalDate inputUntil = LocalDate.parse(parameters.get("inputUntil"));
        String apiHost = parameters.get("apiHost");
        String apiPort = parameters.get("apiPort");

        List<String> acceptedActivityTypes = Arrays.asList(acceptedActivityTypesStr.split(","));

        final FileSource<Activity> source =
                FileSource.forRecordStreamFormat(
                                AvroParquetReaders.forSpecificRecord(Activity.class),
                                Path.fromLocalFile(new File(inputDataPath)))
                        .build();

        final FileSink<AggregatedActivities> sink = FileSink
                .forBulkFormat(Path.fromLocalFile(new File(outputDataPath)),
                        AvroParquetWriters.forSpecificRecord(AggregatedActivities.class))
                .build();

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.enableCheckpointing(10L);
        env.setRuntimeMode(RuntimeExecutionMode.BATCH);

        final DataStream<Activity> stream =
                env.fromSource(source, WatermarkStrategy.noWatermarks(), "file-source")
                        .filter(activity -> {
                            var activityStartDate = new Timestamp(activity.getStartTimestamp()).toLocalDateTime().toLocalDate();
                            var activityEndDate = Optional.ofNullable(activity.getEndTimestamp())
                                    .map(endTimestamp -> new Timestamp(endTimestamp).toLocalDateTime().toLocalDate())
                                    .orElse(activityStartDate);

                            return (activityStartDate.isEqual(inputFrom) || activityStartDate.isAfter(inputFrom))
                                    && (activityEndDate.isEqual(inputUntil) || activityEndDate.isBefore(inputUntil));
                        })
                        .filter(ac -> Objects.nonNull(ac.getActivityType()) &&
                                acceptedActivityTypes.contains(ac.getActivityType().toString()));

        AsyncDataStream.orderedWait(stream,
                        new AsyncTestEnvironmentsApiFunction(apiHost, apiPort),
                        10000, TimeUnit.MILLISECONDS, 1)
                .map(tuple -> new MappedActivity(
                        tuple.f0.getUserId().toString(),
                        tuple.f0.getEnvironment().toString(),
                        tuple.f0.getActivityType().toString(),
                        tuple.f0.getStartTimestamp(),
                        Optional.ofNullable(tuple.f0.getEndTimestamp()),
                        tuple.f1.map(apiRes -> LocalDate.parse(apiRes.getActiveFrom())),
                        tuple.f1.map(apiRes -> LocalDate.parse(apiRes.getActiveUntil())),
                        tuple.f1.map(TestEnvironmentApiResponse::getEnvironment)
                ))
                .filter(mappedActivity -> mappedActivity.getEnvResponseEnv().isPresent()
                        && mappedActivity.getEnvironment().equals(mappedActivity.getEnvResponseEnv().get()))
                .filter(mappedActivity -> !mappedActivity.shouldActivityBeIgnored())
                .keyBy(new KeySelector<MappedActivity, Tuple2<String, String>>() {
                    @Override
                    public Tuple2<String, String> getKey(MappedActivity mappedActivity) {
                        return Tuple2.of(mappedActivity.getUserId(), mappedActivity.getEnvironment());
                    }
                })

                .window(TumblingProcessingTimeWindows.of(Time.hours(1)))
                .process(new ProcessWindowFunction<MappedActivity, AggregatedActivities, Tuple2<String, String>, TimeWindow>() {
                    @Override
                    public void process(Tuple2<String, String> key,
                                        ProcessWindowFunction<MappedActivity, AggregatedActivities, Tuple2<String, String>,
                                                TimeWindow>.Context context, Iterable<MappedActivity> elements,
                                        Collector<AggregatedActivities> out) {
                        List<SimplifiedActivity> simplifiedActivities = new ArrayList<>();
                        elements
                                .forEach(activity -> simplifiedActivities.add(new SimplifiedActivity(
                                        activity.getActivityType(),
                                        activity.getStartTimestamp(),
                                        activity.getEndTimestamp().orElse(null))));

                        AggregatedActivities aggregatedActivities =
                                new AggregatedActivities(key.f0, key.f1, simplifiedActivities);
                        out.collect(aggregatedActivities);
                    }
                })
                .sinkTo(sink);

        env.execute("Flink Java API WalkMe Solution");
    }
}
