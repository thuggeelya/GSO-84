package org.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class SortAlphabetic implements Sort {

    @Override
    public void sort(String[] text) {
        writeFile("sortAlphabetic.txt", Arrays.stream(text)
                .sorted(Comparator.comparing(this::onlyAlphabeticAndDigit))
                .collect(Collectors.toList())
        );
    }
}