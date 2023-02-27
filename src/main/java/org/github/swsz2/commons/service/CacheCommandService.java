package org.github.swsz2.commons.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CacheCommandService {
    @CacheEvict(cacheNames = "DUMMY", key = "#key")
    public String insert(String key) {
        return UUID.randomUUID().toString();
    }
}

