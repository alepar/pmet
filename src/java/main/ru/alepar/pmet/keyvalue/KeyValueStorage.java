package ru.alepar.pmet.keyvalue;

public interface KeyValueStorage<K extends StorageKey, V> {

    V get(K key);
    void put(K key, V value);

}
