package ru.alepar.pmet.metrickey;

public interface FilterKey {
    boolean matches(MetricKey key);
}
