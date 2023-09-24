package com.walkme.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Slf4j
public class TestEnvironmentsApiClient {


    private final String apiHost;
    private final String apiPort;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Cache<String, TestEnvironmentApiResponse> cache = Caffeine.newBuilder()
            .maximumSize(10000)
            .build();

    public CompletableFuture<Optional<TestEnvironmentApiResponse>> getFor(String userId) {
        TestEnvironmentApiResponse resultFromCache = cache.getIfPresent(userId);
        if (Objects.nonNull(resultFromCache)) {
            log.info("Retrieved user test environments data {} from cache: {}", userId, resultFromCache);
            return CompletableFuture.completedFuture(Optional.of(resultFromCache));
        } else {
            String requestUrl = String.format("http://%s:%s/testEnvironments/%s", apiHost, apiPort, userId);
            log.info("GET API Call: {}", requestUrl);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(requestUrl))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();
            return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(body -> {
                        try {
                            return Optional.of(objectMapper.readValue(body, TestEnvironmentApiResponse.class));
                        } catch (JsonProcessingException e) {
                            log.error("Json mapping exception", e);
                            return Optional.empty();
                        }
                    });
        }
    }


}
