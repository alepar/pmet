package ru.alepar.pmet.keyvalue.lmdb;

import org.fusesource.lmdbjni.Database;
import org.fusesource.lmdbjni.Env;
import ru.alepar.pmet.keyvalue.KeyValueStorage;
import ru.alepar.pmet.serializer.Serializer;

public class LmdbKeyValueStorage<V> implements KeyValueStorage<LmdbStorageKey, V> {

    private final Serializer<V> serializer;

    private final Env env;
    private final Database db;

    public LmdbKeyValueStorage(String path, Serializer<V> serializer) {
        this.serializer = serializer;

        env = new Env(path);
        db = env.openDatabase();
    }

    @Override
    public V get(LmdbStorageKey key) {
        final byte[] valueBytes = db.get(key.bytes());
        return serializer.deserialize(valueBytes);
    }

    @Override
    public void put(LmdbStorageKey key, V value) {
        db.put(key.bytes(), serializer.serialize(value));
    }

}
