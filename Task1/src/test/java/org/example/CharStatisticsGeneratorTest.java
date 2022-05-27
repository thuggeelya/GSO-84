package org.example;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CharStatisticsGeneratorTest {
    private static final String PREFIX = "src/test/resources/";

    @Test
    public void checkIfExists() throws IOException {
        String inp = PREFIX + "input.txt";
        String out = PREFIX + "output.txt";
        CharStatisticsGenerator generator = new CharStatisticsGenerator();
        generator.generate(inp, out);
        Path path = Path.of(out);
        assertTrue("file doesn't exist: " + out, Files.exists(path));
        deleteFiles(path);
    }

    @Test
    public void executeTempFileStatisticsGenerator() throws IOException {
        TempCollector tempCollector = new TempCollector();
        CharStatisticsGenerator generator = new CharStatisticsGenerator();
        String outputPath = PREFIX + "tempOutput.txt";
        generator.generate(PREFIX + tempCollector.getTempFile().getFileName().toString(), outputPath);
        assertEquals(tempCollector.getMap(), generator.getFileReader().getCollector().getMap());
        deleteFiles(tempCollector.getTempFile(), Path.of(outputPath));
    }

    @Test
    public void executeRealFileStatisticsGenerator() {
        String inp = PREFIX + "input.txt";
        String out = PREFIX + "output.txt";
        CharStatisticsGenerator generator = new CharStatisticsGenerator();
        generator.generate(inp, out);
        assertEquals(RealCollector.MAP, generator.getFileReader().getCollector().getMap());
    }

    private void deleteFiles(Path... paths) throws IOException {
        for (Path p : paths) {
            Files.deleteIfExists(p);
        }
    }
}