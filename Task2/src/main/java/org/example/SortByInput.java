package org.example;

import org.sort.IFileSortingStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

/**
 * Sorts the text using sorting strategies.
 * @see org.sort.IFileSortingStrategy
 */
public class SortByInput {

    /**
     * Sorts the text given in {@code inputFile} according to the
     * <i>sorting strategy</i>, defined in {@code fullyQualifiedClass} implements
     * {@link org.sort.IFileSortingStrategy}, and writes it into {@code outputFile}.
     * @param inputFile Content path to input file
     * @param outputFile Content path to output file
     * @param fullyQualifiedClass Sorting class canonical name
     * @throws ClassNotFoundException if {@code fullyQualifiedClass} is missing
     * @see #sort(String fullyQualifiedClass)
     * @see org.sort.IFileSortingStrategy
     */
    public static void sort(String inputFile, String outputFile, String fullyQualifiedClass) throws ClassNotFoundException {
        try {
            Class<?> c = Class.forName(fullyQualifiedClass);
            IFileSortingStrategy instance = (IFileSortingStrategy) c.getDeclaredConstructor().newInstance();
            instance.sort(instance.readFile(inputFile), outputFile);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Logger.getGlobal().severe("Something went wrong: " + e);
        }
    }

    /**
     * Sorts text given in file according to the
     * sorting strategy, defined in {@code fullyQualifiedClass} implements
     * {@link org.sort.IFileSortingStrategy}, and writes it into another file.
     * File paths need to be given from terminal.
     * @param fullyQualifiedClass Sorting class canonical name
     * @throws ClassNotFoundException if {@code fullyQualifiedClass} is missing
     */
    public static void sort(String fullyQualifiedClass) throws ClassNotFoundException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Input file:");
            String inputFile = br.readLine();
            System.out.println("Output file:");
            String outputFile = br.readLine();
            sort(inputFile, outputFile, fullyQualifiedClass);
        } catch (IOException e) {
            Logger.getGlobal().severe("Something went wrong: " + e);
            e.printStackTrace();
        }
    }
}