package ru.alepar.pmet.keyvalue.lmdb;

import ru.alepar.pmet.keyvalue.StorageKey;

public class LmdbStorageKey implements StorageKey {
    public byte[] bytes() {
        throw new RuntimeException();
    }
}
