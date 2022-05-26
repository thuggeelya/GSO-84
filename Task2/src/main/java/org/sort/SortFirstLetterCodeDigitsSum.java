package org.sort;

import java.util.Comparator;

@SuppressWarnings("unused")
public class SortFirstLetterCodeDigitsSum implements IFileSortingStrategy {

    @Override
    public Comparator<String> getSortingStrategy() {
        return Comparator.comparingInt(this::getSumDigits).thenComparing(Comparator.naturalOrder());
    }
}