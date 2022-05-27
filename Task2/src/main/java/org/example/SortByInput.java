package org.example;

import org.sort.IFileSortingStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

public class SortByInput {

    public static void sort(String fileName, String outputFileName, String fullyQualifiedClass) {
        try {
            Class<?> c = Class.forName(fullyQualifiedClass);
            IFileSortingStrategy instance = (IFileSortingStrategy) c.getDeclaredConstructor().newInstance();
            instance.sort(instance.readFile(fileName), outputFileName);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            Logger.getGlobal().severe("Something went wrong: " + e);
        }
    }

    public static void sort(String fullyQualifiedClass) {
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