package com.couriertracking.config;


import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCache courierCache = new CaffeineCache("courier",
                Caffeine.newBuilder()
                        .expireAfterWrite(1, TimeUnit.MINUTES)
                        .maximumSize(100)
                        .build());

        CaffeineCache storeCache = new CaffeineCache("store",
                Caffeine.newBuilder()
                        .expireAfterWrite(1, TimeUnit.DAYS)
                        .maximumSize(100)
                        .build());

        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(List.of(courierCache, storeCache));
        return manager;
    }
}

