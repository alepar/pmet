package ru.alepar.pmet.metrickey;

import ru.alepar.pmet.keyvalue.StorageKey;

import java.util.Collection;

/**
 * stores a list of all metrices for given application along with their properties
 * provides simplistic search capability
 */
public interface MetricKeyDirectory<K extends StorageKey> {

    Collection<K> find(FilterKey filter);
    K getOrAdd(MetricKey key);

}
