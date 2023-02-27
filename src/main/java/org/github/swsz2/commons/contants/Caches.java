package org.github.swsz2.commons.contants;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Caches {

    public static final CacheOption DUMMY;
    public static final List<CacheOption> CACHE_OPTIONS;

    static {
        CACHE_OPTIONS = new ArrayList<>();

        DUMMY = new CacheOption("DUMMY", 1, 1, 1);
        CACHE_OPTIONS.add(DUMMY);
    }


    @Getter
    public static class CacheOption {
        CacheOption(String cacheName, int expireAfterWrite, int refreshAfterWrite, int maximumSize) {
            this.cacheName = cacheName;
            this.expireAfterWrite = expireAfterWrite;
            this.refreshAfterWrite = refreshAfterWrite;
            this.maximumSize = maximumSize;
        }

        private final String cacheName;
        private final int expireAfterWrite;
        private final int refreshAfterWrite;
        private final int maximumSize;
    }
}
