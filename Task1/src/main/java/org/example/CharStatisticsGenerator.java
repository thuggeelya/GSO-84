package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public final class CharStatisticsGenerator {

    private FileReader fileReader;
    private CharStatisticsWriter charStatisticsWriter;

    public FileReader getFileReader() {
        return fileReader;
    }

    public CharStatisticsWriter getCharStatisticsWriter() {
        return charStatisticsWriter;
    }

    public void generate() {
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

    public void generate(String inputFilePath, String outputFilePath) {
        start(inputFilePath, outputFilePath);
    }

    private void start(String inputFilePath, String outputFilePath) {
        ICharStatisticsCollector collector = new CharStatisticsCollector();
        fileReader = new FileReader(inputFilePath, collector);
        fileReader.readFile();
        charStatisticsWriter = new CharStatisticsWriter(collector.getValues(), outputFilePath);
        charStatisticsWriter.writeCharStatistics();
    }
}