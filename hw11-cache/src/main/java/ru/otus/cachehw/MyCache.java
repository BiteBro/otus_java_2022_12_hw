package ru.otus.cachehw;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class MyCache<K, V> implements HwCache<K, V> {

    private final List<HwListener<K, V>> listeners;
    private final WeakHashMap<K, V> cache;

    public MyCache() {
        this.listeners = new ArrayList<>();
        this.cache = new WeakHashMap<>();
    }

    @Override
    public void put(K key, V value) {
        this.cache.put(key, value);
        executeListeners(key, value, "PUT");
    }

    @Override
    public void remove(K key) {
        V value = this.cache.remove(key);
        executeListeners(key, value, "REMOVE");
    }

    @Override
    public V get(K key) {
        V value = this.cache.get(key);
        executeListeners(key, value, "GET");
        return  value;
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        listeners.remove(listener);
    }

    private void executeListeners(K key, V value, String action) {
        listeners.forEach(listener -> listener.notify(key, value, action));
    }
}
