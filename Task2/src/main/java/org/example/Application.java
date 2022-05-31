package org.example;

import org.sort.Dictionary;
import org.sort.ISortingStrategy;
import org.sort.MyFileReader;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Logger;

public class Application {

    /**
     * Sorts the text given in input file according to the
     * <i>sorting strategy</i>, defined in fully qualified strategy class implements
     * {@link ISortingStrategy}, and writes it into the output file.
     *
     * @throws ClassNotFoundException if {@code strategy} class is missing
     * @see ISortingStrategy
     */
    public static void main(String[] args) throws ClassNotFoundException {
        String inputFile = args[0];
        String fullyQualifiedClass = args[1];

        try {
            Class<?> c = Class.forName(fullyQualifiedClass);
            ISortingStrategy instance = (ISortingStrategy) c.getDeclaredConstructor().newInstance();
            Dictionary dictionary = new Dictionary();
            MyFileReader reader = new MyFileReader(inputFile, dictionary);
            reader.readFile();
            List<String> sortedWords = instance.sort(dictionary);
            sortedWords.forEach(System.out::println);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            Logger.getGlobal().severe(e.getMessage());
        }
    }
}