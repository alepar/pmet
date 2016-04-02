package ru.alepar.pmet.serializer;

public interface Serializer<V> {
    V deserialize(byte[] bytes);

    byte[] serialize(V value);
}
