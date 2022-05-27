package org.example;

import org.junit.Test;
import org.sort.FileReadable;
import org.sort.SortAlphabetic;
import org.sort.SortAlphabeticSorted;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.example.SortByInput.sort;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class SortByInputTest
{
    private static final String PREFIX = "src/test/resources/";
    @Test
    public void checkIfExists() throws IOException, ClassNotFoundException {
        String inp  = PREFIX + "input.txt";
        String out1 = PREFIX + "output1.txt";
        String out2 = PREFIX + "output2.txt";
        sort(inp, out1, SortAlphabetic.class.getCanonicalName());
        sort(inp, out2, SortAlphabeticSorted.class.getCanonicalName());
        Path path1 = Path.of(out1);
        Path path2 = Path.of(out2);
        assertTrue("file doesn't exist: " + out1, Files.exists(path1));
        assertTrue("file doesn't exist: " + out2, Files.exists(path2));
        deleteFiles(out1, out2);
    }

    @Test
    public void sortTempFile() throws IOException, ClassNotFoundException {
        String outputActual = PREFIX + "output.txt";
        String outputTemp   = PREFIX + "tempOutput.txt";
        TempSort tempSort = new TempSort();
        tempSort.writeRandomText();
        testStrategy(tempSort, outputActual, outputTemp, SortAlphabetic.class.getCanonicalName());
        testStrategy(tempSort, outputActual, outputTemp, "org.sort.SortAlphabeticTurned");
        testStrategy(tempSort, outputActual, outputTemp, "org.sort.SortLength");
        testStrategy(tempSort, outputActual, outputTemp, "org.sort.SortAlphabeticSorted");
        testStrategy(tempSort, outputActual, outputTemp, "org.sort.SortFirstLetterCodeDigitsSum");
        deleteFiles(tempSort.getTempFile().toString());
    }

    private void testStrategy(TempSort tempSort, String outputActual, String outputTemp, String strategy) throws IOException, ClassNotFoundException {
        // sort by temp
        tempSort.setStrategy(strategy);
        tempSort.sortTemp(outputTemp);
        // sort by main
        sort(tempSort.getTempFile().toString(), outputActual, strategy);
        FileReadable readable = new SortAlphabetic();
        assertArrayEquals("files don't match: " + outputActual + ", " + outputTemp, readable.readFile(outputActual), readable.readFile(outputTemp));
        deleteFiles(outputActual, outputTemp);
    }

    private void deleteFiles(String... paths) throws IOException {
        for (String s : paths) {
            Files.deleteIfExists(Path.of(s));
        }
    }
}