package org.example;

import org.statistics.CharCount;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class TempCollector {

    static final String PATH = "src/test/resources/";
    private static final String CHARS = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
    private final Map<Character, CharCount> charStatisticsMap = new HashMap<>();
    private Path tempFile;

    public TempCollector() throws IOException {
        this.collectRandomChars();
    }

    public Path getTempFile() {
        return tempFile;
    }

    /**
     * @return dynamically collected char statistics
     */
    public Map<Character, CharCount> getMap() {
        return charStatisticsMap;
    }

    private void collectRandomChars() throws IOException {
        char ch;
        StringBuilder line = new StringBuilder();

        for (int i = 0; i < 25; i++) {
            ch = CHARS.charAt((int) (Math.random() * CHARS.length()));
            line.append(ch);

            if (ch != '\n') {
                charStatisticsMap
                        .computeIfAbsent(ch, key -> new CharCount(key, new AtomicLong(0)))
                        .getCount()
                        .incrementAndGet();
            }
        }

        Path tempFile = Files.createTempFile(Paths.get(PATH), "temp", ".txt");
        this.tempFile = tempFile;
        Files.write(tempFile, Collections.singleton(line.toString()));
    }
}