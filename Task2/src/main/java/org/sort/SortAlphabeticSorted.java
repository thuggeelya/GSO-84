package org.sort;

import java.util.Arrays;
import java.util.Comparator;

@SuppressWarnings("unused")
public class SortAlphabeticSorted implements ISortingStrategy {

    @Override
    public Comparator<String> getSortingStrategy() {
        return Comparator.comparing(this::alphabetize).thenComparing(Comparator.naturalOrder());
    }

    private String alphabetize(String word) {
        char[] wordChars = word.toCharArray();
        Arrays.sort(wordChars);
        return new String(wordChars);
    }
}