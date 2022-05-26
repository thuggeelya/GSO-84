package org.sort;

import java.util.Comparator;

@SuppressWarnings("unused")
public class SortLength implements IFileSortingStrategy {

    @Override
    public Comparator<String> getSortingStrategy() {
        return Comparator.comparingInt(this::getNonPunctuationalLength).thenComparing(Comparator.naturalOrder());
    }
}