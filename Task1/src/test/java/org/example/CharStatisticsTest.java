package org.example;

import org.junit.Test;
import org.statistics.CharStatisticsCollector;
import org.statistics.CharStatisticsWriter;
import org.statistics.FileReader;
import org.statistics.ICharStatisticsCollector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.example.RealCollector.MAP;
import static org.example.TempCollector.PATH;
import static org.junit.Assert.assertEquals;

public class CharStatisticsTest {

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
        checkMapsEquality(PATH + tempCollector.getTempFile().getFileName().toString(), outputPath, tempCollector.getMap());
        deleteFiles(tempCollector.getTempFile(), Paths.get(outputPath));
    }

    /**
     * Compares the {@code CharStatisticsCollector} result with the
     * {@code RealCollector.MAP} known in advance.
     *
     * @see RealCollector
     */
    @Test
    public void executeRealFileStatistics() throws IOException {
        String outputPath = "output.txt";
        checkMapsEquality(PATH + "input.txt", outputPath, MAP);
        deleteFiles(Paths.get(outputPath));
    }

    private void checkMapsEquality(String inputFile, String outputFile, Map<?, ?> mapToCompare) {
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