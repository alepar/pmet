package ru.alepar.pmet.metrickey;

import ru.alepar.pmet.keyvalue.StorageKey;
import ru.alepar.pmet.keyvalue.StorageKeyFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimpleListMetricKeyDirectory<K extends StorageKey> implements MetricKeyDirectory<K> {

    private final List<MetricKey> keys = new ArrayList<>();
    private final StorageKeyFactory<K> keyFactory;

    public SimpleListMetricKeyDirectory(StorageKeyFactory<K> keyFactory) {
        this.keyFactory = keyFactory;
    }

    @Override
    public Collection<K> find(FilterKey filter) {
        final List<K> foundKeys = new ArrayList<>();

        int i=0;
        for (MetricKey key : keys) {
            if (filter.matches(key)) {
                foundKeys.add(createKey(i));
            }
            i++;
        }

        return foundKeys;
    }

    @Override
    public K getOrAdd(MetricKey key) {
        int i=0;
        for (MetricKey entry : keys) {
            if(key.equals(entry)) {
                return createKey(i);
            }
            i++;
        }

        keys.add(key);
        return createKey(i);
    }

    private K createKey(int i) {
        return keyFactory.create(i);
    }

}
