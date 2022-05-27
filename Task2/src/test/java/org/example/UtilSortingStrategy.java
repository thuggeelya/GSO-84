package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UtilSortingStrategy {

    public static List<String> getLines(List<String> words, String strategyClassFullyQualified) {
        Stream<String> stream = words.stream();

        switch (strategyClassFullyQualified) {
            case "org.sort.SortAlphabetic" -> stream = stream.sorted(getSortingStrategyAlpha());
            case "org.sort.SortAlphabeticTurned" -> stream = stream.sorted(getSortingStrategyAlphaTurned());
            case "org.sort.SortAlphabeticSorted" -> stream = stream.sorted(getSortingStrategyAlphaSorted());
            case "org.sort.SortFirstLetterCodeDigitsSum" -> stream = stream.sorted(getSortingStrategyLetterCode());
            case "org.sort.SortLength" -> stream = stream.sorted(getSortingStrategyLength());
        }

        return stream.collect(Collectors.toList());
    }

    private static Comparator<String> getSortingStrategyAlpha() {
        return Comparator.comparing(UtilSortingStrategy::makeOnlyAlphabeticAndDigit);
    }

    private static Comparator<String> getSortingStrategyAlphaTurned() {
        return Comparator.comparing(UtilSortingStrategy::reverse).thenComparing(Comparator.naturalOrder());
    }

    private static Comparator<String> getSortingStrategyAlphaSorted() {
        return Comparator.comparing(UtilSortingStrategy::alphabetize).thenComparing(Comparator.naturalOrder());
    }

    private static Comparator<String> getSortingStrategyLetterCode() {
        return Comparator.comparingInt(UtilSortingStrategy::getSumDigits).thenComparing(Comparator.naturalOrder());
    }

    private static Comparator<String> getSortingStrategyLength() {
        return Comparator.comparingInt(UtilSortingStrategy::getNonPunctuationalLength).thenComparing(Comparator.naturalOrder());
    }

    private static String makeOnlyAlphabeticAndDigit(String s) {
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

    private static String reverse(String s) {
        String alpha = makeOnlyAlphabeticAndDigit(s);
        return isBlank(alpha) ? s : new StringBuilder(alpha).reverse().toString();
    }

    private static String alphabetize(String s) {
        String alpha = makeOnlyAlphabeticAndDigit(s);

        if (isBlank(alpha)) {
            return s;
        }

        char[] wordChars = alpha.toCharArray();
        Arrays.sort(wordChars);
        return new String(wordChars);
    }

    private static int getSumDigits(String s) {
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

    private static int getNonPunctuationalLength(String s) {
        return isBlank(s) ? 0 : makeOnlyAlphabeticAndDigit(s).length();

    }

    private static boolean isBlank(String s) {
        return (s == null) || (s.isBlank());
    }
}