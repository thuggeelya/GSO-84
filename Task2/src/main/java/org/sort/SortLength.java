package org.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class SortLength implements FileEditable {

    @Override
    public void sort(String[] text) {
        writeFile("sortLength.txt", Arrays.stream(text)
                .sorted(Comparator.comparingInt(this::getNonPunctuationalLength).thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toList())
        );
    }

    private int getNonPunctuationalLength(String s) {
        if (isBlank(s)) {
            return 0;
        }

        return onlyAlphabeticAndDigit(s).length();
    }
}