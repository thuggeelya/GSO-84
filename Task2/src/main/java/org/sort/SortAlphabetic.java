package org.sort;

import java.util.Comparator;

@SuppressWarnings("unused")
public class SortAlphabetic implements ISortingStrategy {

    @Override
    public Comparator<String> getSortingStrategy() {
        return Comparator.naturalOrder();
    }


}