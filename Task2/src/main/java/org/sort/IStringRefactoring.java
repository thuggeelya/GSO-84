package org.sort;

import java.util.Arrays;

public interface IStringRefactoring {

    default String makeOnlyAlphabeticAndDigit(String s) {
        if (isBlank(s)) {
            return s;
        }

        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (Character.isAlphabetic(c) || Character.isDigit(c)) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    default String alphabetize(String s) {
        String alpha = makeOnlyAlphabeticAndDigit(s);

        if (isBlank(alpha)) {
            return s;
        }

        char[] wordChars = alpha.toCharArray();
        Arrays.sort(wordChars);
        return new String(wordChars);
    }

    default String reverse(String s) {
        String alpha = makeOnlyAlphabeticAndDigit(s);
        return isBlank(alpha) ? s : new StringBuilder(alpha).reverse().toString();
    }

    default int getSumDigits(String s) {
        String alpha = makeOnlyAlphabeticAndDigit(s);

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

    default int getNonPunctuationalLength(String s) {
        return isBlank(s) ? 0 : makeOnlyAlphabeticAndDigit(s).length();

    }

    default boolean isBlank(String s) {
        return (s == null) || (s.isBlank());
    }
}