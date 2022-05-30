package org.example;

import org.sort.*;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

public class Application {

    public static void main(String[] args) throws ClassNotFoundException {
        String in = args[0];
        String out = args[1];
        sort(SortLength.class.getCanonicalName(), in, out + "Length.txt");
        sort(SortAlphabetic.class.getCanonicalName(), in, out + "Alpha.txt");
        sort(SortAlphabeticSorted.class.getCanonicalName(), in, out + "AlphaSorted.txt");
        sort(SortAlphabeticTurned.class.getCanonicalName(), in, out + "AlphaTurned.txt");
        sort(SortFirstLetterCodeDigitsSum.class.getCanonicalName(), in, out + "CodeSum.txt");
    }

    /**
     * Sorts the text given in {@code inputFile} according to the
     * <i>sorting strategy</i>, defined in strategy class implements
     * {@link org.sort.IFileSortingStrategy}, and writes it into {@code outputFile}.
     *
     * @param inputFile  Content path to input file
     * @param outputFile Content path to output file
     * @param fullyQualifiedClass   Sorting class canonical name
     * @throws ClassNotFoundException if {@code strategy} class is missing
     * @see org.sort.IFileSortingStrategy
     */
    private static void sort(String fullyQualifiedClass, String inputFile, String outputFile) throws ClassNotFoundException {
        try {
            Class<?> c = Class.forName(fullyQualifiedClass);
            IFileSortingStrategy instance = (IFileSortingStrategy) c.getDeclaredConstructor().newInstance();
            instance.sort(instance.readFile(inputFile), outputFile);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            Logger.getGlobal().severe(e.getMessage());
        }
    }
}