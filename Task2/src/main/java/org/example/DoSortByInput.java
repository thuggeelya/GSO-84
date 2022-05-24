package org.example;

import org.sort.FileEditable;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

public class DoSortByInput {

    public static void sort(String fileName, String fullyQualifiedClass) {
        try {
            Class<?> c = Class.forName(fullyQualifiedClass);
            FileEditable instance = (FileEditable) c.getDeclaredConstructor().newInstance();
            instance.sort(instance.readFile(fileName));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Logger.getGlobal().severe("Something went wrong: " + e);
        }
    }
}