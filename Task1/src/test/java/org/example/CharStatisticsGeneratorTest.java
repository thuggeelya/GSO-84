package org.example;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CharStatisticsGeneratorTest
{
    @Before
    public void generate() {
        String inp = "src/test/resources/input.txt";
        CharStatisticsGenerator.generate(inp, "src/test/resources/output1.txt");
        CharStatisticsGenerator.generate(inp, "src/test/resources/output2.txt");
    }

    @Test
    public void charStatisticsGenerator() throws IOException {
        Path out1 = Path.of("src/test/resources/output1.txt");
        Path out2 = Path.of("src/test/resources/output2.txt");
        assertTrue(Files.exists(out1));
        assertTrue(Files.exists(out2));
        assertEquals(Files.size(out1), Files.size(out2));
    }
}
