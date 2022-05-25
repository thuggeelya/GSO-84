package org.sort;

import java.util.Comparator;

@SuppressWarnings("unused")
public class SortAlphabeticTurned implements IFileSortingStrategy {

    private String reversed(String s) {
        String alpha = onlyAlphabeticAndDigit(s);

        if (isBlank(alpha)) {
            return s;
        }

        return new StringBuilder(alpha).reverse().toString();
    }

    @Override
    public Comparator<String> sortingStrategy() {
        return Comparator.comparing(this::reversed).thenComparing(Comparator.naturalOrder());
    }
}