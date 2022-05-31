package org.sort;

import java.util.HashSet;
import java.util.Set;

public class Dictionary {

    private final Set<String> words = new HashSet<>();

    public void addToDictionary(String word) {
        words.add(word);
    }

    public Set<String> getWords() {
        return words;
    }

    @Override
    public String toString() {
        return "WordsDictionary{" +
                "words=" + words +
                '}';
    }
}