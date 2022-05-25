package org.sort;

import java.util.Comparator;

@SuppressWarnings("unused")
public class SortAlphabetic implements IFileSortingStrategy {

    @Override
    public Comparator<String> sortingStrategy() {
        return Comparator.comparing(this::onlyAlphabeticAndDigit);
    }
}