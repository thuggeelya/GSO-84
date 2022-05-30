package org.statistics;

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
            while (is.available() > 0) {
                collector.collect((char) is.read());
            }
        } catch (IOException e) {
            Logger.getGlobal().severe(e.getMessage());
        }
    }
}