package ru.alepar.pmet.metrickey;

import java.util.Map;

import static ru.alepar.pmet.metrickey.MapFilterKey.toMap;

public class MapMetricKey implements MetricKey {

    private final Map<String, String> entries;

    public MapMetricKey(String... strings) {
        entries = toMap(strings);
    }

    @Override
    public Map<String, String> asMap() {
        return entries;
    }
}
