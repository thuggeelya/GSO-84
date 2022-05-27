package org.example;

import org.sort.SortLength;

import static org.example.SortByInput.sort;

public class Application {

    public static void main(String[] args) throws ClassNotFoundException {
        sort(SortLength.class.getCanonicalName());
    }
}