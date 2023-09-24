package com.walkme.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

@Data
@AllArgsConstructor
public class MappedActivity {

    private String userId;
    private String environment;
    private String activityType;
    private Long startTimestamp;
    private Optional<Long> endTimestamp;
    private Optional<LocalDate> envActiveFrom;
    private Optional<LocalDate> envActiveUntil;
    private Optional<String> envResponseEnv;


    public boolean shouldActivityBeIgnored() {
        var startDate = new Timestamp(startTimestamp).toLocalDateTime().toLocalDate();
        var endDate = endTimestamp
                .map(endTimestamp -> new Timestamp(endTimestamp).toLocalDateTime().toLocalDate()).orElse(startDate);

        return envActiveUntil.isEmpty() || (endDate.isAfter(envActiveUntil.get()));

    }

}
