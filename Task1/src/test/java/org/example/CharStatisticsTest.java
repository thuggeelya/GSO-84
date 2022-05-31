package org.example;

import org.junit.Test;
import org.statistics.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertEquals;

public class CharStatisticsTest {

    static final String RESOURCES = "src/test/resources/";
    public static final Map<Character, CharCount> MAP = new HashMap<>();

    static {
        MAP.put('T', new CharCount('T', new AtomicLong(1)));
        MAP.put('h', new CharCount('h', new AtomicLong(1)));
        MAP.put('e', new CharCount('e', new AtomicLong(3)));
        MAP.put(' ', new CharCount(' ', new AtomicLong(2)));
        MAP.put('r', new CharCount('r', new AtomicLong(2)));
        MAP.put('a', new CharCount('a', new AtomicLong(1)));
        MAP.put('l', new CharCount('l', new AtomicLong(3)));
        MAP.put('c', new CharCount('c', new AtomicLong(2)));
        MAP.put('o', new CharCount('o', new AtomicLong(2)));
        MAP.put('t', new CharCount('t', new AtomicLong(1)));
    }

    /**
     * Creates temporary file using {@code TempCollector} class and
     * collects char statistics dynamically. Then compares {@code TempCollector.charStatisticsMap}
     * with the {@code CharStatisticsCollector} result on the temporary file.
     *
     * @throws IOException if an I/O error occurs or dir does not exist
     */
    @Test
    public void executeTempFileStatistics() throws IOException {
        TempCollector tempCollector = new TempCollector();
        String outputPath = "tempOutput.txt";
        checkMapsEquality(tempCollector.getTempFile().toString(), outputPath, tempCollector.getMap());
        deleteFiles(tempCollector.getTempFile(), Paths.get(outputPath));
    }

    /**
     * Compares the {@code CharStatisticsCollector} result with the
     * {@code MAP} known in advance.
     */
    @Test
    public void executeRealFileStatistics() throws IOException {
        String outputPath = "output.txt";
        checkMapsEquality(RESOURCES + "input.txt", outputPath, MAP);
        deleteFiles(Paths.get(outputPath));
    }

    private void checkMapsEquality(String inputFile, String outputFile, Map<Character, CharCount> mapToCompare) {
        ICharStatisticsCollector collector = new CharStatisticsCollector();
        FileReader fileReader = new FileReader(inputFile, collector);
        fileReader.readFile();
        new CharStatisticsWriter(collector.getValues(), outputFile).writeCharStatistics();
        assertEquals(mapToCompare, fileReader.getCollector().getMap());
    }

    private void deleteFiles(Path... paths) throws IOException {
        for (Path p : paths) {
            Files.deleteIfExists(p);
        }
    }
}