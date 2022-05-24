package org.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class SortAlphabeticTurned implements FileEditable {

    @Override
    public void sort(String[] text) {
        writeFile("sortAlphabeticTurned.txt", Arrays.stream(text)
                .sorted(Comparator.comparing(this::reversed).thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toList())
        );
    }

    private String reversed(String s) {
        String alpha = onlyAlphabeticAndDigit(s);

        if (isBlank(alpha)) {
            return s;
        }

        return new StringBuilder(alpha).reverse().toString();
    }
}