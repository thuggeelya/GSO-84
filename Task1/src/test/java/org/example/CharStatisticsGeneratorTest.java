package org.example;

import org.junit.Test;
import org.statistics.CharStatisticsGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.example.RealCollector.MAP;
import static org.junit.Assert.assertEquals;

public class CharStatisticsGeneratorTest {
    private static final String PREFIX = "src/test/resources/";

    /**
     * Creates temporary file using {@code TempCollector} class and
     * collects char statistics dynamically. Then compares {@code TempCollector.charStatisticsMap}
     * with the {@code CharStatisticsGenerator} result on the temporary file.
     *
     * @throws IOException if an I/O error occurs or dir does not exist
     */
    @Test
    public void executeTempFileStatisticsGenerator() throws IOException {
        TempCollector tempCollector = new TempCollector();
        CharStatisticsGenerator generator = new CharStatisticsGenerator();
        String outputPath = PREFIX + "tempOutput.txt";
        generator.generate(PREFIX + tempCollector.getTempFile().getFileName().toString(), outputPath);
        assertEquals(tempCollector.getMap(), generator.getFileReader().getCollector().getMap());
        deleteFiles(tempCollector.getTempFile(), Path.of(outputPath));
    }

    /**
     * Compares the {@code CharStatisticsGenerator} result with the
     * {@code RealCollector.MAP} known in advance.
     *
     * @see RealCollector
     */
    @Test
    public void executeRealFileStatisticsGenerator() throws IOException {
        String inp = PREFIX + "input.txt";
        String out = PREFIX + "output.txt";
        CharStatisticsGenerator generator = new CharStatisticsGenerator();
        generator.generate(inp, out);
        assertEquals(MAP, generator.getFileReader().getCollector().getMap());
        deleteFiles(Path.of(out));
    }

    private void deleteFiles(Path... paths) throws IOException {
        for (Path p : paths) {
            Files.deleteIfExists(p);
        }
    }
}