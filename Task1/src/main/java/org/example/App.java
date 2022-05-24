package org.example;

public class App {
    public static void main( String[] args ) {
        String fileName = "input.txt";
        String outputFileName = "output.txt";
        new StatisticsGenerator(fileName, outputFileName).writeCharStatistics();
    }
}