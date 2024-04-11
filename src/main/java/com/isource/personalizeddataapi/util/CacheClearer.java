package com.isource.personalizeddataapi.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CacheClearer {

    @CacheEvict(value = "productCache", allEntries = true)
    @Scheduled(fixedRateString = "${product.cache.ttl}")
    public void emptyHotelsCache() {
        log.info("emptying product cache");
    }
}
