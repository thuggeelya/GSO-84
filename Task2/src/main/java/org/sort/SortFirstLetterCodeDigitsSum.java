package org.sort;

import java.util.Comparator;

@SuppressWarnings("unused")
public class SortFirstLetterCodeDigitsSum implements IFileSortingStrategy {


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

    @Override
    public Comparator<String> sortingStrategy() {
        return Comparator.comparingInt(this::sumDigits).thenComparing(Comparator.naturalOrder());
    }
}