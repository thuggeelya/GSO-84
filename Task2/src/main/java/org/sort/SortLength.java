package org.sort;

import java.util.Comparator;

@SuppressWarnings("unused")
public class SortLength implements ISortingStrategy {

    @Override
    public Comparator<String> getSortingStrategy() {
        return Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder());
    }
}