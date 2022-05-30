package org.example;

import org.statistics.CharStatisticsCollector;
import org.statistics.CharStatisticsWriter;
import org.statistics.FileReader;
import org.statistics.ICharStatisticsCollector;

public class Application {

    public static void main(String[] args) {
        ICharStatisticsCollector collector = new CharStatisticsCollector();
        new FileReader(args[0], collector).readFile();
        new CharStatisticsWriter(collector.getValues(), args[1]).writeCharStatistics();
    }
}