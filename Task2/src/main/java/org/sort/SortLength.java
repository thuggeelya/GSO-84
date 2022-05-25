package org.sort;

import java.util.Comparator;

@SuppressWarnings("unused")
public class SortLength implements IFileSortingStrategy {

    private int getNonPunctuationalLength(String s) {
        if (isBlank(s)) {
            return 0;
        }

        return onlyAlphabeticAndDigit(s).length();
    }

    @Override
    public Comparator<String> sortingStrategy() {
        return Comparator.comparingInt(this::getNonPunctuationalLength).thenComparing(Comparator.naturalOrder());
    }
}