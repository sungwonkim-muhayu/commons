package org.github.swsz2.commons.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.github.swsz2.commons.contants.Caches;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@EnableCaching
@Configuration
public class CacheConfiguration {
    @Bean
    public CacheManager cacheManager() {
        List<CaffeineCache> caches = Caches.CACHE_OPTIONS.stream()
                .map(option -> new CaffeineCache(option.getCacheName(), Caffeine.newBuilder().recordStats() // 캐시 통계 설정
                                .expireAfterWrite(option.getExpireAfterWrite(), TimeUnit.SECONDS) // 생성 또는 값 변경 후 n초 이후 제거
                                .refreshAfterWrite(option.getRefreshAfterWrite(), TimeUnit.SECONDS) // 특정 시간 이후 데이터를 다시 읽음
                                .maximumSize(option.getMaximumSize())
                                .build()
                        )
                )
                .collect(Collectors.toList());

        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(caches);

        return cacheManager;
    }
}
