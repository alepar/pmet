package ru.alepar.pmet.metric;

import ru.alepar.pmet.metrickey.FilterKey;
import ru.alepar.pmet.metrickey.MetricKey;

import java.util.Collection;

/**
 * stores histograms for metrices
 */
public interface MetricStorage {


    //TODO time
    Collection<HistogramWrapper> find(FilterKey filter);

    void add(MetricKey entry, HistogramWrapper histogram);

}
