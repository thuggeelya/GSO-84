package org.example;

import org.junit.Test;
import org.sort.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class SortByInputTest {

    public static final List<String> ALPHABETIC = new ArrayList<String>() {
        {
            add("-12");
            add("404");
            add("boo-boo");
            add("boo404");
            add("Да");
            add("нас");
            add("окружает");
            add("постоянно");
            add("хаос");
        }
    };

    public static final List<String> ALPHABETIC_TURNED = new ArrayList<String>() {
        {
            add("-12");
            add("404");
            add("boo404");
            add("boo-boo");
            add("Да");
            add("постоянно");
            add("нас");
            add("хаос");
            add("окружает");
        }
    };

    public static final List<String> ALPHABETIC_SORTED = new ArrayList<String>() {
        {
            add("-12");
            add("boo-boo");
            add("404");
            add("boo404");
            add("Да");
            add("окружает");
            add("нас");
            add("хаос");
            add("постоянно");
        }
    };

    public static final List<String> LENGTH = new ArrayList<String>() {
        {
            add("Да");
            add("-12");
            add("404");
            add("нас");
            add("хаос");
            add("boo404");
            add("boo-boo");
            add("окружает");
            add("постоянно");
        }
    };

    public static final List<String> FIRST_LETTER_CODE_SUM = new ArrayList<String>() {
        {
            add("-12");
            add("Да");
            add("нас");
            add("окружает");
            add("постоянно");
            add("хаос");
            add("404");
            add("boo-boo");
            add("boo404");
        }
    };

    private static final String INPUT_FILE = "src/test/resources/input.txt";

    /**
     * Compares the sorting using information known in advance.
     *
     * @throws ClassNotFoundException if fullyQualifiedClass is missing
     */
    @Test
    public void compareWithRealSort() throws ClassNotFoundException {
        assertEquals("Alphabetic sort failed", ALPHABETIC, sort(SortAlphabetic.class.getCanonicalName()));
        assertEquals("Alphabetic turned sort failed", ALPHABETIC_TURNED, sort(SortAlphabeticTurned.class.getCanonicalName()));
        assertEquals("Alphabetic sorted sort failed", ALPHABETIC_SORTED, sort(SortAlphabeticSorted.class.getCanonicalName()));
        assertEquals("Length sort failed", LENGTH, sort(SortLength.class.getCanonicalName()));
        assertEquals("First letter code sum sort failed", FIRST_LETTER_CODE_SUM, sort(SortFirstLetterCodeDigitsSum.class.getCanonicalName()));
    }

    private List<String> sort(String fullyQualifiedClass) throws ClassNotFoundException {
        try {
            Class<?> c = Class.forName(fullyQualifiedClass);
            ISortingStrategy instance = (ISortingStrategy) c.getDeclaredConstructor().newInstance();
            Dictionary dictionary = new Dictionary();
            MyFileReader reader = new MyFileReader(INPUT_FILE, dictionary);
            reader.readFile();
            return instance.sort(dictionary);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            Logger.getGlobal().severe(e.getMessage());
        }

        return new ArrayList<>();
    }
}