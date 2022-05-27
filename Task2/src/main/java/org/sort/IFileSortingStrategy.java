package org.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Strategy to sort text in <i>files</i>.
 *
 * The {@code IFileSortingStrategy} interface provides two methods for text sorting.
 * The user can persist the output path in signature or using terminal.
 */
public interface IFileSortingStrategy extends FileWritable, FileReadable{

    /**
     * @return <i>sorting strategy</i> as a {@code Comparator}
     */
    Comparator<String> getSortingStrategy();

    /**
     * Sorts the words of input text
     * and writes the result into the <i>file</i>.
     *
     * File path needs to be given from terminal.
     * @param text A {@code String} array consisting of input text words
     */
    default void sort(String[] text) {
        writeFile(getLines(text));
    }

    /**
     * Sorts the words of input text
     * and writes the result into the <i>file</i>.
     *
     * @param text A {@code String} array consisting of input text words
     * @param outputFileName An output file content path
     */
    default void sort(String[] text, String outputFileName) {
        writeFile(outputFileName, getLines(text));
    }

    /**
     * @param text An array containing words from read file
     * @return List sorted by <i>sorting strategy</i>.
     * @see #getSortingStrategy()
     */
    default List<String> getLines(String[] text) {
        return Arrays.stream(text)
                .sorted(getSortingStrategy())
                .collect(Collectors.toList());
    }
}