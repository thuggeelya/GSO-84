package org.example;

import org.sort.Sort;

import java.lang.reflect.InvocationTargetException;

public class DoSortByInput {

    public static void sort(String fileName, String fullyQualifiedClass) {
        try {
            Class<?> c = Class.forName(fullyQualifiedClass);
            Sort instance = (Sort) c.getDeclaredConstructor().newInstance();
            instance.sort(instance.readFile(fileName));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            System.out.println("Something went wrong..");
        }
    }
}