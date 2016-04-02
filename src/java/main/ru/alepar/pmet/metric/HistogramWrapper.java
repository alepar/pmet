package ru.alepar.pmet.metric;

/**
 * wrapper for HdrHistogram classes, which facilitates polymorphic adding
 */
public interface HistogramWrapper {
    void add(HistogramWrapper toAdd);
}
