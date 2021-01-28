package com.spaient.assesment.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class CacheLRU<K, V> extends LinkedHashMap<K,V> {

    private int capacity;

    public CacheLRU(Integer cacheSize) {
        super(cacheSize, 0.75f, true);
        this.capacity = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return this.size() > this.capacity;
    }

}

