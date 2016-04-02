package ru.alepar.pmet.metric;

import ru.alepar.pmet.keyvalue.KeyValueStorage;
import ru.alepar.pmet.keyvalue.StorageKey;
import ru.alepar.pmet.metrickey.FilterKey;
import ru.alepar.pmet.metrickey.MetricKey;
import ru.alepar.pmet.metrickey.MetricKeyDirectory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DirectoryMetricStorage<K extends StorageKey> implements MetricStorage {

    private final KeyValueStorage<K, HistogramWrapper> storage;
    private final MetricKeyDirectory<K> metricDirectory;

    public DirectoryMetricStorage(KeyValueStorage<K, HistogramWrapper> storage, MetricKeyDirectory<K> metricDirectory) {
        this.storage = storage;
        this.metricDirectory = metricDirectory;
    }

    @Override
    public Collection<HistogramWrapper> find(FilterKey filter) {
        final Collection<K> skeys = metricDirectory.find(filter);
        final List<HistogramWrapper> histograms = new ArrayList<>(skeys.size());
        for (K skey : skeys) {
            final HistogramWrapper histogramWrapper = storage.get(skey);
            if (histogramWrapper != null) {
                histograms.add(histogramWrapper);
            }
        }
        return histograms;
    }

    @Override
    public void add(MetricKey key, HistogramWrapper toAdd) {
        final K skey = metricDirectory.getOrAdd(key);
        final HistogramWrapper existing = storage.get(skey);

        final HistogramWrapper toPut;
        if (existing != null) {
            toPut = existing;
            existing.add(toAdd);
        } else {
            toPut = toAdd;
        }

        storage.put(skey, toPut);
    }
}
