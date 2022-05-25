package org.example;

import static org.example.DoSortByInput.sort;

public class App {

    public static void main( String[] args ) {
        sort("Task2/src/main/resources/input.txt", "Task2/src/main/resources/1.txt", "org.sort.SortAlphabetic");
        sort("Task2/src/main/resources/input.txt", "Task2/src/main/resources/2.txt", "org.sort.SortLength");
        sort("Task2/src/main/resources/input.txt", "Task2/src/main/resources/3.txt", "org.sort.SortAlphabeticTurned");
        sort("org.sort.SortAlphabeticSorted");
        sort("org.sort.SortFirstLetterCodeDigitsSum");
    }
}