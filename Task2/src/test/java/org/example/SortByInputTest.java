package org.example;

import org.junit.Test;
import org.sort.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.example.RealSort.*;
import static org.example.SortByInput.sort;
import static org.junit.Assert.assertArrayEquals;

public class SortByInputTest {
    private static final String PREFIX = "src/test/resources/";

    /**
     * Sorts created temporary file using {@code TempSort} class.
     * Then compares the contents of temporary output and real output ({@link #read(String file)}).
     *
     * @throws IOException if an I/O error occurs or dir does not exist
     * @throws ClassNotFoundException if fullyQualifiedClass is missing
     */
    @Test
    public void sortTempFile() throws IOException, ClassNotFoundException {
        String outputActual = PREFIX + "output.txt";
        String outputTemp = PREFIX + "tempOutput.txt";
        TempSort tempSort = new TempSort();
        tempSort.writeRandomText();
        testStrategy(tempSort, outputActual, outputTemp, SortAlphabetic.class.getCanonicalName());
        testStrategy(tempSort, outputActual, outputTemp, SortAlphabeticTurned.class.getCanonicalName());
        testStrategy(tempSort, outputActual, outputTemp, SortLength.class.getCanonicalName());
        testStrategy(tempSort, outputActual, outputTemp, SortAlphabeticSorted.class.getCanonicalName());
        testStrategy(tempSort, outputActual, outputTemp, SortFirstLetterCodeDigitsSum.class.getCanonicalName());
        deleteFiles(tempSort.getTempFile().toString());
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

    /**
     * Compares the sorting using {@code RealSort} class arrays known in advance.
     *
     * @throws ClassNotFoundException if fullyQualifiedClass is missing
     * @throws IOException if an I/O error occurs or dir does not exist
     */
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

    private String[] read(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();

            while (line != null) {
                list.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list.toArray(new String[0]);
    }

    private void deleteFiles(String... paths) throws IOException {
        for (String s : paths) {
            Files.deleteIfExists(Path.of(s));
        }
    }
}