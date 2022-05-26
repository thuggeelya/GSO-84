package org.sort;

import java.util.Comparator;

@SuppressWarnings("unused")
public class SortAlphabeticSorted implements IFileSortingStrategy {

    @Override
    public Comparator<String> getSortingStrategy() {
        return Comparator.comparing(this::alphabetize).thenComparing(Comparator.naturalOrder());
    }
}