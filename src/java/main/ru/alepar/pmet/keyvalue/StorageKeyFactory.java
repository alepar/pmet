package ru.alepar.pmet.keyvalue;

public interface StorageKeyFactory<K extends StorageKey> {
    K create(int value);
}
