package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public final class CharStatisticsGenerator {

    /*
    Task1/src/main/resources/input.txt
    Task1/src/main/resources/output.txt
     */

    public static void generate() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Откуда считывать:");
            String inputFilePath = br.readLine();
            System.out.println("Куда записывать:");
            String outputFilePath = br.readLine();
            start(inputFilePath, outputFilePath);
        } catch (IOException e) {
            Logger.getGlobal().severe("Something went wrong: " + e);
        }
    }

    public static void generate(String inputFilePath, String outputFilePath) {
        start(inputFilePath, outputFilePath);
    }

    private static void start(String inputFilePath, String outputFilePath) {
        ICharStatisticsCollector collector = new CharStatisticsCollector();
        new FileReader(inputFilePath, collector).readFile();
        new CharStatisticsWriter(collector.packOutput(), outputFilePath).writeCharStatistics();
    }
}