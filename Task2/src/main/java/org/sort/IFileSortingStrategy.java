package org.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * A strategy to sort text in <i>files</i>.
 *
 * <p>The {@code IFileSortingStrategy} interface provides the method for text sorting.</p>
 *
 * @see org.sort.FileReadable
 * @see org.sort.FileWritable
 */
public interface IFileSortingStrategy extends FileWritable, FileReadable {

    /**
     * @return <i>sorting strategy</i> as a {@code Comparator} with {@code String} type
     */
    Comparator<String> getSortingStrategy();

    /**
     * Sorts the words of the given text
     * and writes the result into the <i>file</i>.
     *
     * @param text           A {@code String} array consisting of input text words
     * @param outputFileName An output file content path
     */
    default void sort(String[] text, String outputFileName) {
        writeFile(outputFileName,
                Arrays.stream(text)
                        .sorted(getSortingStrategy())
                        .collect(Collectors.toList()));
    }
}