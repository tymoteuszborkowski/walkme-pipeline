package com.walkme.services;


import com.walkme.domain.avro.Activity;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.apache.flink.streaming.api.functions.async.RichAsyncFunction;

import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Slf4j
public class AsyncTestEnvironmentsApiFunction
        extends RichAsyncFunction<Activity, Tuple2<Activity, Optional<TestEnvironmentApiResponse>>> {

    private transient TestEnvironmentsApiClient testEnvironmentsApiClient;

    private final String apiHost;
    private final String apiPort;

    public AsyncTestEnvironmentsApiFunction(String apiHost, String apiPort) {
        this.apiHost = apiHost;
        this.apiPort = apiPort;
    }

    @Override
    public void open(Configuration parameters) {
        testEnvironmentsApiClient = new TestEnvironmentsApiClient(apiHost, apiPort);
    }

    @Override
    public void asyncInvoke(Activity key,
                            ResultFuture<Tuple2<Activity, Optional<TestEnvironmentApiResponse>>> resultFuture) {
        CompletableFuture<Optional<TestEnvironmentApiResponse>> userEnvironments =
                testEnvironmentsApiClient.getFor(key.getUserId().toString());

        CompletableFuture.supplyAsync(() -> {
            try {
                return userEnvironments.get();
            } catch (InterruptedException | ExecutionException e) {
                log.error("Exception during test env API fetch", e);
                return Optional.<TestEnvironmentApiResponse>empty();
            }
        }).thenAccept((Optional<TestEnvironmentApiResponse> res) ->
                resultFuture.complete(Collections.singleton(new Tuple2<>(key, res))));

    }

    @Override
    public void timeout(Activity input,
                        ResultFuture<Tuple2<Activity, Optional<TestEnvironmentApiResponse>>> resultFuture) throws Exception {
        super.timeout(input, resultFuture);
    }
}
