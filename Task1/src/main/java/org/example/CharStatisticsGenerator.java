package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public final class CharStatisticsGenerator {

    private FileReader fileReader;

    public FileReader getFileReader() {
        return fileReader;
    }

    @SuppressWarnings("unused")
    public void generate() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Input file:");
            String inputFilePath = br.readLine();
            System.out.println("Output file:");
            String outputFilePath = br.readLine();
            generate(inputFilePath, outputFilePath);
        } catch (IOException e) {
            Logger.getGlobal().severe("Something went wrong: " + e);
        }
    }

    public void generate(String inputFilePath, String outputFilePath) {
        ICharStatisticsCollector collector = new CharStatisticsCollector();
        fileReader = new FileReader(inputFilePath, collector);
        fileReader.readFile();
        CharStatisticsWriter charStatisticsWriter = new CharStatisticsWriter(collector.getValues(), outputFilePath);
        charStatisticsWriter.writeCharStatistics();
    }
}