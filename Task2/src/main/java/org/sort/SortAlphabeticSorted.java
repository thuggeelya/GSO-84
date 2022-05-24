package org.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class SortAlphabeticSorted implements FileEditable {

    @Override
    public void sort(String[] text) {
        writeFile("sortAlphabeticSorted.txt", Arrays.stream(text)
                .sorted(Comparator.comparing(this::alphabetize).thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toList())
        );
    }

    private String alphabetize(String s) {
        String alpha = onlyAlphabeticAndDigit(s);

        if (isBlank(alpha)) {
            return s;
        }

        char[] wordChars = alpha.toCharArray();
        Arrays.sort(wordChars);
        return new String(wordChars);
    }
}