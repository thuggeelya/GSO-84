package org.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface ISortingStrategy {

    /**
     * @return <i>sorting strategy</i> as a {@code Comparator} with {@code String} type
     */
    Comparator<String> getSortingStrategy();

    default List<String> sort(Dictionary dictionary) {
        List<String> words = new ArrayList<>(dictionary.getWords());
        words.sort(getSortingStrategy());
        return words;
    }
}