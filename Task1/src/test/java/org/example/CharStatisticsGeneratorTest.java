package org.example;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CharStatisticsGeneratorTest
{
    @Test
    public void executeCharStatisticsGenerator() throws IOException
    {
        String inp = "src/test/resources/input.txt";
        String out1 = "src/test/resources/output1.txt";
        String out2 = "src/test/resources/output2.txt";
        CharStatisticsGenerator generator1 = new CharStatisticsGenerator();
        CharStatisticsGenerator generator2 = new CharStatisticsGenerator();
        generator1.generate(inp, out1);
        generator2.generate(inp, out2);
        Path path1 = Path.of(out1);
        Path path2 = Path.of(out2);
        assertTrue("file doesn't exist: " + out1, Files.exists(path1));
        assertTrue("file doesn't exist: " + out2, Files.exists(path2));

        Map<Character, CharCount> map1 = generator1.getFileReader().getCollector().getMap();
        Map<Character, CharCount> map2 = generator2.getFileReader().getCollector().getMap();
        assertEquals("numbers are not equal", map1.get('a').getCount().get(),
                map2.get('a').getCount().get());
        assertEquals("numbers are not equal", map1.get('I').getCount().get(),
                map2.get('I').getCount().get());
        deleteFiles(path1, path2);
    }

    @Test
    public void executeTempFileStatisticsGenerator() throws IOException
    {
        String prefix = "src/test/resources/";
        TempCollector tempCollector = new TempCollector();
        CharStatisticsGenerator generator = new CharStatisticsGenerator();
        String outputPath = prefix + "tempOutput.txt";
        generator.generate(prefix + tempCollector.getTempFile().getFileName().toString(), outputPath);
        assertEquals(tempCollector.getMap(), generator.getFileReader().getCollector().getMap());
        deleteFiles(tempCollector.getTempFile(), Path.of(outputPath));
    }

    private void deleteFiles(Path... paths) throws IOException {
        for (Path p : paths) {
            Files.deleteIfExists(p);
        }
    }
}