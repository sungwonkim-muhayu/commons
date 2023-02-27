package org.github.swsz2.commons.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheQueryService {

    @Cacheable(cacheNames = "DUMMY", key = "#key")
    public void delete(String key) {
    }
}
