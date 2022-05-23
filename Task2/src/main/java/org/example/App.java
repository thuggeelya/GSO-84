package org.example;

import static org.example.DoSortByInput.sort;

public class App {

    public static void main( String[] args ) {
        sort("input.txt", "org.sort.SortAlphabetic");
        sort("input.txt", "org.sort.SortLength");
        sort("input.txt", "org.sort.SortAlphabeticTurned");
        sort("input.txt", "org.sort.SortAlphabeticSorted");
        sort("input.txt", "org.sort.SortFirstLetterCodeDigitsSum");
        sort("input.txt", "no class");
    }
}