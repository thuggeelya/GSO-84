package org.sort;

import java.util.Comparator;

@SuppressWarnings("unused")
public class SortAlphabeticTurned implements IFileSortingStrategy {

    @Override
    public Comparator<String> getSortingStrategy() {
        return Comparator.comparing(this::reverse).thenComparing(Comparator.naturalOrder());
    }
}