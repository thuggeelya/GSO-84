package org.example;

import org.junit.Test;
import org.sort.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.example.RealSort.*;
import static org.example.TempSort.PATH;
import static org.junit.Assert.assertArrayEquals;

public class SortByInputTest {

    /**
     * Compares the sorting using {@link RealSort} class arrays known in advance.
     *
     * @throws ClassNotFoundException if fullyQualifiedClass is missing
     * @throws IOException            if an I/O error occurs or dir does not exist
     */
    @Test
    public void compareWithRealSort() throws ClassNotFoundException, IOException {
        String inp = PATH + "input.txt";
        String out = "output.txt";
        sort(inp, out, SortAlphabetic.class.getCanonicalName());
        assertArrayEquals("alphabetic sort failed", ALPHABETIC, read(out));
        sort(inp, out, SortAlphabeticTurned.class.getCanonicalName());
        assertArrayEquals("alphabetic turned sort failed", ALPHABETIC_TURNED, read(out));
        sort(inp, out, SortAlphabeticSorted.class.getCanonicalName());
        assertArrayEquals("alphabetic sorted sort failed", ALPHABETIC_SORTED, read(out));
        sort(inp, out, SortLength.class.getCanonicalName());
        assertArrayEquals("length sort failed", LENGTH, read(out));
        sort(inp, out, SortFirstLetterCodeDigitsSum.class.getCanonicalName());
        assertArrayEquals("first letter code sum sort failed", FIRST_LETTER_CODE_SUM, read(out));
        deleteFiles(out);
    }

    private void sort(String inputFile, String outputFile, String fullyQualifiedClass) throws ClassNotFoundException {
        try {
            Class<?> c = Class.forName(fullyQualifiedClass);
            IFileSortingStrategy instance = (IFileSortingStrategy) c.getDeclaredConstructor().newInstance();
            instance.sort(instance.readFile(inputFile), outputFile);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            Logger.getGlobal().severe(e.getMessage());
        }
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
            Files.deleteIfExists(Paths.get(s));
        }
    }
}