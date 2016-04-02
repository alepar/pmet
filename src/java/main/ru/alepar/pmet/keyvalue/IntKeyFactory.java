package ru.alepar.pmet.keyvalue;

public class IntKeyFactory implements StorageKeyFactory<IntKeyFactory.IntStorageKey> {
    @Override
    public IntKeyFactory.IntStorageKey create(int value) {
        return new IntStorageKey(value);
    }

    static class IntStorageKey implements StorageKey {

        private final int value;

        IntStorageKey(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            IntStorageKey that = (IntStorageKey) o;

            return value == that.value;

        }

        @Override
        public int hashCode() {
            return value;
        }
    }
}
