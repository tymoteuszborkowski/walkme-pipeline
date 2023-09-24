package com.walkme.services;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestEnvironmentApiResponse {

    private String userId;
    private String environment;
    private String activeFrom;
    private String activeUntil;

}
