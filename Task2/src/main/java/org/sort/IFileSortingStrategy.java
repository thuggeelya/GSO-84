package org.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public interface IFileSortingStrategy extends FileWritable, FileReadable{

    Comparator<String> sortingStrategy();

    default void sort(String[] text) {
        writeFile(getLines(text));
    }

    default void sort(String[] text, String outputFileName) {
        writeFile(outputFileName, getLines(text));
    }

    default List<String> getLines(String[] text) {
        return Arrays.stream(text).sorted(sortingStrategy()).collect(Collectors.toList());
    }
}