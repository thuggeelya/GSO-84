package org.sort;

import java.util.Comparator;

@SuppressWarnings("unused")
public class SortAlphabeticTurned implements ISortingStrategy {

    @Override
    public Comparator<String> getSortingStrategy() {
        return Comparator.comparing(this::reverse).thenComparing(Comparator.naturalOrder());
    }

    private String reverse(String word) {
        return new StringBuilder(word).reverse().toString();
    }
}