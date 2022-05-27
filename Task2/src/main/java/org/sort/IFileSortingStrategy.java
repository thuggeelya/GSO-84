package org.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A strategy to sort text in <i>files</i>.
 *
 * <p>The {@code IFileSortingStrategy} interface provides two methods for text sorting.</p>
 * The user can persist the output path in signature or using terminal.
 * @see org.sort.FileReadable
 * @see org.sort.FileWritable
 */
public interface IFileSortingStrategy extends FileWritable, FileReadable{

    /**
     * @return <i>sorting strategy</i> as a {@code Comparator} with {@code String} type
     */
    Comparator<String> getSortingStrategy();

    /**
     * Sorts the words of the given text
     * and writes the result into the <i>file</i>.
     * <p>File path needs to be given from terminal.</p>
     * @param text A {@code String} array consisting of input text words
     */
    default void sort(String[] text) {
        writeFile(getLines(text));
    }

    /**
     * Sorts the words of the given text
     * and writes the result into the <i>file</i>.
     * @param text A {@code String} array consisting of input text words
     * @param outputFileName An output file content path
     */
    default void sort(String[] text, String outputFileName) {
        writeFile(outputFileName, getLines(text));
    }

    /**
     * @param text An array containing words from read file
     * @return List sorted by <i>sorting strategy</i>
     * @see #getSortingStrategy()
     */
    default List<String> getLines(String[] text) {
        return Arrays.stream(text)
                .sorted(getSortingStrategy())
                .collect(Collectors.toList());
    }
}