package org.sort;

import java.util.Comparator;

@SuppressWarnings("unused")
public class SortFirstLetterCodeDigitsSum implements ISortingStrategy {

    @Override
    public Comparator<String> getSortingStrategy() {
        return Comparator.comparingInt(this::getFirstCharCodeSumDigits).thenComparing(Comparator.naturalOrder());
    }

    private int getFirstCharCodeSumDigits(String word) {
        int n = Character.getNumericValue(word.charAt(0));

        if (n < 10) {
            return n;
        }

        int sum = 0;

        for (int c : String.valueOf(n).toCharArray()) {
            sum += c;
        }

        return sum;
    }
}