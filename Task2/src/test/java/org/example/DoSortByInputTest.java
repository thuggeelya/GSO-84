package org.example;

import org.junit.Test;
import org.sort.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.example.DoSortByInput.sort;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class DoSortByInputTest
{
    @Test
    public void exists() throws IOException {
        String inp = "src/test/resources/input.txt";
        String out1 = "src/test/resources/output1.txt";
        String out2 = "src/test/resources/output2.txt";
        sort(inp, out1, SortAlphabetic.class.getCanonicalName());
        sort(inp, out2, SortAlphabeticSorted.class.getCanonicalName());
        Path path1 = Path.of(out1);
        Path path2 = Path.of(out2);
        assertTrue(Files.exists(path1));
        assertTrue(Files.exists(path2));
        deleteFiles(out1, out2);
    }

    @Test
    public void tempFileSort() throws IOException {
        String outputActual = "src/test/resources/output.txt";
        String outputTemp = "src/test/resources/tempOutput.txt";
        TempSort tempSort = new TempSort();
        tempSort.writeRandomText();
        testStrategy(tempSort, outputActual, outputTemp, new SortAlphabetic());
        testStrategy(tempSort, outputActual, outputTemp, new SortAlphabeticTurned());
        testStrategy(tempSort, outputActual, outputTemp, new SortLength());
        testStrategy(tempSort, outputActual, outputTemp, new SortAlphabeticSorted());
        testStrategy(tempSort, outputActual, outputTemp, new SortFirstLetterCodeDigitsSum());
        deleteFiles(tempSort.getTempFile().toString());
    }

    private void testStrategy(TempSort tempSort, String outputActual, String outputTemp, IFileSortingStrategy strategy) throws IOException {
        tempSort.setStrategy(strategy);
        tempSort.sortTemp(outputTemp);
        sort(tempSort.getTempFile().toString(), outputActual, strategy.getClass().getCanonicalName());
        FileReadable readable = new SortAlphabetic();
        assertArrayEquals(readable.readFile(outputActual), readable.readFile(outputTemp));
        deleteFiles(outputActual, outputTemp);
    }

    private void deleteFiles(String... paths) throws IOException {
        for (String s : paths) {
            Files.deleteIfExists(Path.of(s));
        }
    }
}
