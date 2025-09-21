package com.example.coffee_backend.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class RateLimitingService {

    @Value("${rate.limit.login.requests:5}")
    private int loginRequests;

    @Value("${rate.limit.login.duration:60}")
    private int loginDuration;

    @Value("${rate.limit.api.requests:100}")
    private int apiRequests;

    @Value("${rate.limit.api.duration:60}")
    private int apiDuration;

    private final Cache<String, Bucket> loginBuckets = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(1, TimeUnit.HOURS)
            .build();

    private final Cache<String, Bucket> apiBuckets = Caffeine.newBuilder()
            .maximumSize(100_000)
            .expireAfterWrite(1, TimeUnit.HOURS)
            .build();

    public Bucket getLoginBucket(String key) {
        return loginBuckets.get(key, k -> createLoginBucket());
    }

    public Bucket getApiBucket(String key) {
        return apiBuckets.get(key, k -> createApiBucket());
    }

    private Bucket createLoginBucket() {
        Bandwidth limit = Bandwidth.classic(loginRequests, Refill.intervally(loginRequests, Duration.
                ofSeconds(loginDuration)));
        return Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

    private Bucket createApiBucket() {
        Bandwidth limit = Bandwidth.classic(apiRequests, Refill.intervally(apiRequests, Duration.
                ofSeconds(apiDuration)));
        return Bucket4j.builder()
                .addLimit(limit)
                .build();
    }
}