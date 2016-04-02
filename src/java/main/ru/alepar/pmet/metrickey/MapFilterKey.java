package ru.alepar.pmet.metrickey;

import java.util.HashMap;
import java.util.Map;

public class MapFilterKey implements FilterKey {

    private final Map<String, String> entries;

    public MapFilterKey(String... strings) {
        entries = toMap(strings);
    }

    @Override
    public boolean matches(MetricKey key) {
        final Map<String, String> keyMap = key.asMap();
        final Map<String, String> filterMap = this.entries;

        if (filterMap.size() > keyMap.size()) {
            return false;
        }

        for (Map.Entry<String, String> entry : filterMap.entrySet()) {
            final String setValue = keyMap.get(entry.getKey());

            if (!entry.getValue().equals(setValue)) {
                return false;
            }
        }

        return true;
    }

    static Map<String, String> toMap(String[] strings) {
        Map<String, String> entries;
        if (strings.length % 2 != 0) {
            throw new IllegalArgumentException();
        }

        entries = new HashMap<>();
        for(int i=0; i<strings.length/2; i++) {
            entries.put(strings[2*i], strings[2*i+1]);
        }
        return entries;
    }

}
