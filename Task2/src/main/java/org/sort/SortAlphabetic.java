package org.sort;

import java.util.Comparator;

@SuppressWarnings("unused")
public class SortAlphabetic implements IFileSortingStrategy {

    @Override
    public Comparator<String> getSortingStrategy() {
        return Comparator.comparing(this::makeOnlyAlphabeticAndDigit).thenComparing(Comparator.naturalOrder());
    }
}