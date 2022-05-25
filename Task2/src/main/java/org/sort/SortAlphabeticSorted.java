package org.sort;

import java.util.Arrays;
import java.util.Comparator;

@SuppressWarnings("unused")
public class SortAlphabeticSorted implements IFileSortingStrategy {

    private String alphabetize(String s) {
        String alpha = onlyAlphabeticAndDigit(s);

        if (isBlank(alpha)) {
            return s;
        }

        char[] wordChars = alpha.toCharArray();
        Arrays.sort(wordChars);
        return new String(wordChars);
    }

    @Override
    public Comparator<String> sortingStrategy() {
        return Comparator.comparing(this::alphabetize).thenComparing(Comparator.naturalOrder());
    }
}