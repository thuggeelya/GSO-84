package org.sort;

public interface IStringRefactoring {

    default String onlyAlphabeticAndDigit(String s) {
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

    default boolean isBlank(String s) {
        return (s == null) || (s.isBlank());
    }
}