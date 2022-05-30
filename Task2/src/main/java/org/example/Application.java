package org.example;

import org.sort.*;

import static org.example.SortByInput.sort;

public class Application {

    public static void main(String[] args) throws ClassNotFoundException {
        sort(SortLength.class.getCanonicalName());
        sort(SortAlphabetic.class.getCanonicalName());
        sort(SortAlphabeticSorted.class.getCanonicalName());
        sort(SortAlphabeticTurned.class.getCanonicalName());
        sort("Task2/src/main/resources/input.txt",
                "Task2/src/main/resources/SortFirstLetterCodeDigitsSum.txt",
                SortFirstLetterCodeDigitsSum.class.getCanonicalName());
    }
}