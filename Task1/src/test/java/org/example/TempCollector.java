package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

public class TempCollector {

    private static final String CHARS = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
    private final Map<Character, CharCount> charStatisticsMap = new TreeMap<>();
    private Path tempFile;

    public TempCollector() throws IOException {
        this.collectRandomChars();
    }

    public Path getTempFile() {
        return tempFile;
    }

    public Map<Character, CharCount> getMap() {
        return charStatisticsMap;
    }

    public void collectRandomChars() throws IOException {
        char v;
        StringBuilder line = new StringBuilder();

        for (int i = 0; i < 25; i++) {
            v = CHARS.charAt((int) (Math.random() * CHARS.length()));
            line.append(v);

            if (v != '\n') {
                CharCount charCount = charStatisticsMap.get(v);

                if (charCount != null) {
                    charStatisticsMap.get(v).getCount().incrementAndGet();
                } else {
                    charStatisticsMap.put(v, new CharCount(v, new AtomicLong(0)));
                }
            }
        }

        Path tempFile = Files.createTempFile(Path.of("src/test/resources/"), "temp", ".txt");
        this.tempFile = tempFile;
        Files.write(tempFile, Collections.singleton(line.toString()));
    }
}