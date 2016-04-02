import org.junit.Test;
import ru.alepar.pmet.keyvalue.IntKeyFactory;
import ru.alepar.pmet.keyvalue.StorageKey;
import ru.alepar.pmet.metrickey.*;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class SimpleListMetricKeyDirectoryTest {

    private final MetricKeyDirectory<?> directory = new SimpleListMetricKeyDirectory(new IntKeyFactory());

    private final MetricKey mkey1 = new MapMetricKey(
        "Host", "some-host",
        "Name", "some-name",
        "Operation", "some-operation"
    );

    private final MetricKey mkey2 = new MapMetricKey(
        "Host", "some-host2",
        "Name", "some-name",
        "Operation", "some-operation"
    );


    @Test
    public void canGetWhatIAdd() throws Exception {
        final StorageKey skey1 = directory.getOrAdd(mkey1);
        final StorageKey skey2 = directory.getOrAdd(mkey2);

        // keys have to be different
        assertThat(skey1, not(equalTo(skey2)));

        // second call must return the same key
        assertThat(directory.getOrAdd(mkey1), equalTo(skey1));
        assertThat(directory.getOrAdd(mkey2), equalTo(skey2));

        // and the third :)
        assertThat(directory.getOrAdd(mkey1), equalTo(skey1));
        assertThat(directory.getOrAdd(mkey2), equalTo(skey2));
    }

    @Test
    public void canFindWhatIAdd() throws Exception {
        final StorageKey skey1 = directory.getOrAdd(mkey1);
        final StorageKey skey2 = directory.getOrAdd(mkey2);

        final FilterKey bothEntriesFilter = new MapFilterKey(
            "Name", "some-name",
            "Operation", "some-operation"
        );
        assertThat(directory.find(bothEntriesFilter), equalTo(Arrays.asList(skey1, skey2)));

        final FilterKey entry2Filter = new MapFilterKey(
            "Host", "some-host2"
        );
        assertThat(directory.find(entry2Filter), equalTo(Collections.singletonList(skey2)));
    }
}