package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class TempCollector {

    private static final String CHARS = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
    private final Map<Character, CharCount> charStatisticsMap = new HashMap<>();
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
        char ch;
        StringBuilder line = new StringBuilder();

        for (int i = 0; i < 25; i++) {
            ch = CHARS.charAt((int) (Math.random() * CHARS.length()));
            line.append(ch);

            if (ch != '\n') {
                charStatisticsMap.computeIfAbsent(ch, key -> new CharCount(key, new AtomicLong(0)));
                charStatisticsMap.get(ch).getCount().incrementAndGet();
            }
        }

        Path tempFile = Files.createTempFile(Path.of("src/test/resources/"), "temp", ".txt");
        this.tempFile = tempFile;
        Files.write(tempFile, Collections.singleton(line.toString()));
    }
}