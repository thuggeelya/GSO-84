package org.example;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

public class FileReader {

    private final String fullFileName;
    private final ICharStatisticsCollector collector;

    public FileReader(String fullFileName, ICharStatisticsCollector collector) {
        this.fullFileName = fullFileName;
        this.collector = collector;
    }

    public CharStatisticsCollector getCollector() {
        return (CharStatisticsCollector) collector;
    }

    public void readFile() {
        try (InputStream is = new BufferedInputStream(new FileInputStream(this.fullFileName))) {
            int ch = is.read();

            while (ch != -1) {
                collector.collect((char) ch);
                ch = is.read();
            }
        } catch (IOException e) {
            Logger.getGlobal().severe("Something went wrong: " + e);
        }
    }
}