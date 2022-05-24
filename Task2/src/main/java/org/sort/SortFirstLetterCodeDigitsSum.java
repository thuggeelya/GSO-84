package org.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class SortFirstLetterCodeDigitsSum implements FileEditable {

    @Override
    public void sort(String[] text) {
        writeFile("sortFirstLetterCodeDigitsSum.txt", Arrays.stream(text)
                .sorted(Comparator.comparingInt(this::sumDigits).thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toList())
        );
    }

    private int sumDigits(String s) {
        String alpha = onlyAlphabeticAndDigit(s);

        if (isBlank(alpha)) {
            return 0;
        }

        int n = Character.getNumericValue(alpha.charAt(0));

        if (n < 10) {
            return n;
        }

        int sum = 0;

        for (char c : String.valueOf(n).toCharArray()) {
            sum += Integer.parseInt(String.valueOf(c));
        }

        return sum;
    }
}