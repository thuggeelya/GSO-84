package org.example;

import org.junit.Test;
import org.sort.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static org.example.RealSort.*;
import static org.example.SortByInput.sort;
import static org.junit.Assert.assertArrayEquals;

public class SortByInputTest {
    private static final String PREFIX = "src/test/resources/";

    @Test
    public void sortTempFile() throws IOException, ClassNotFoundException {
        String outputActual = PREFIX + "output.txt";
        String outputTemp = PREFIX + "tempOutput.txt";
        TempSort tempSort = new TempSort();
        tempSort.writeRandomText();
        testStrategy(tempSort, outputActual, outputTemp, SortAlphabetic.class.getCanonicalName());
        testStrategy(tempSort, outputActual, outputTemp, "org.sort.SortAlphabeticTurned");
        testStrategy(tempSort, outputActual, outputTemp, "org.sort.SortLength");
        testStrategy(tempSort, outputActual, outputTemp, "org.sort.SortAlphabeticSorted");
        testStrategy(tempSort, outputActual, outputTemp, "org.sort.SortFirstLetterCodeDigitsSum");
        deleteFiles(tempSort.getTempFile().toString());
    }

    @Test
    public void compareWithReal() throws ClassNotFoundException, IOException {
        String inp = PREFIX + "input.txt";
        String outAlphabetic = PREFIX + "1.txt";
        String outAlphabeticTurned = PREFIX + "2.txt";
        String outAlphabeticSorted = PREFIX + "3.txt";
        String outLength = PREFIX + "4.txt";
        String outFirstLetterCodeSum = PREFIX + "5.txt";
        sort(inp, outAlphabetic, SortAlphabetic.class.getCanonicalName());
        sort(inp, outAlphabeticTurned, SortAlphabeticTurned.class.getCanonicalName());
        sort(inp, outAlphabeticSorted, SortAlphabeticSorted.class.getCanonicalName());
        sort(inp, outLength, SortLength.class.getCanonicalName());
        sort(inp, outFirstLetterCodeSum, SortFirstLetterCodeDigitsSum.class.getCanonicalName());
        assertArrayEquals("alphabetic sort failed", ALPHABETIC, read(outAlphabetic));
        assertArrayEquals("alphabetic turned sort failed", ALPHABETIC_TURNED, read(outAlphabeticTurned));
        assertArrayEquals("alphabetic sorted sort failed", ALPHABETIC_SORTED, read(outAlphabeticSorted));
        assertArrayEquals("length sort failed", LENGTH, read(outLength));
        assertArrayEquals("first letter code sum sort failed", FIRST_LETTER_CODE_SUM, read(outFirstLetterCodeSum));
        deleteFiles(outAlphabetic, outAlphabeticTurned, outAlphabeticSorted, outLength, outFirstLetterCodeSum);
    }

    private void testStrategy(TempSort tempSort, String outputActual, String outputTemp, String strategy) throws IOException, ClassNotFoundException {
        // sort by temp
        tempSort.setStrategy(strategy);
        tempSort.sortTemp(outputTemp);
        // sort by main
        sort(tempSort.getTempFile().toString(), outputActual, strategy);
        assertArrayEquals("files don't match: " + outputActual + ", " + outputTemp, read(outputActual), read(outputTemp));
        deleteFiles(outputActual, outputTemp);
    }

    private String[] read(String file) {
        StringBuilder sb = new StringBuilder();
        try (Stream<String> lines = Files.lines(Path.of(file), StandardCharsets.UTF_8)) {
            lines.forEach(sb::append);
        } catch (IOException e) {
            Logger.getGlobal().severe("Something went wrong: " + e);
        }

        System.out.println(sb);
        return sb.toString().split(" ");
    }

    private void deleteFiles(String... paths) throws IOException {
        for (String s : paths) {
            Files.deleteIfExists(Path.of(s));
        }
    }
}