package org.example;

import org.sort.*;

import static org.example.SortByInput.sort;

public class Application {

    public static void main(String[] args) throws ClassNotFoundException {
        sort("Task2/src/main/resources/input.txt", "Task2/src/main/resources/SortLength.txt", SortLength.class.getCanonicalName());
        sort("Task2/src/main/resources/input.txt", "Task2/src/main/resources/SortAlphabetic.txt", SortAlphabetic.class.getCanonicalName());
        sort("Task2/src/main/resources/input.txt", "Task2/src/main/resources/SortAlphabeticSorted.txt", SortAlphabeticSorted.class.getCanonicalName());
        sort("Task2/src/main/resources/input.txt", "Task2/src/main/resources/SortAlphabeticTurned.txt", SortAlphabeticTurned.class.getCanonicalName());
        sort("Task2/src/main/resources/input.txt", "Task2/src/main/resources/SortFirstLetterCodeDigitsSum.txt", SortFirstLetterCodeDigitsSum.class.getCanonicalName());
    }
}