package org.example;

public class App {
    public static void main( String[] args ) {
        String fileName = "input.txt";
        String outputFileName = "output.txt";
        new MyFileReader(fileName, outputFileName).writeCharStatistics();
    }
}