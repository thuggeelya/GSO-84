package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

public class TempCollector {

    private static final String CHARS = "aqwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
    private final ICharStatisticsCollector collector;
    private Path tempFile;

    public TempCollector() throws IOException {
        this.collector = new CharStatisticsCollector();
        this.collect();
    }

    public CharStatisticsCollector getCollector() {
        return (CharStatisticsCollector) collector;
    }

    public Path getTempFile() {
        return tempFile;
    }

    public void collect() throws IOException {
        char v;
        StringBuilder line = new StringBuilder();

        for (int i = 0; i < 25; i++) {
            v = CHARS.charAt((int) (Math.random() * CHARS.length()));
            line.append(v);
            collector.collect(v);
        }

        Path tempFile = Files.createTempFile(Path.of("src/test/resources/"), "temp", ".txt");
        this.tempFile = tempFile;
        Files.write(tempFile, Collections.singleton(line.toString()));
    }
}