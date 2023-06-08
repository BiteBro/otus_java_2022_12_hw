package ru.otus.cachehw;

import java.util.WeakHashMap;

public interface HwCache<K, V> {

    void put(K key, V value);

    void remove(K key);

    V get(K key);

    void addListener(HwListener<K, V> listener);

    void removeListener(HwListener<K, V> listener);
}
