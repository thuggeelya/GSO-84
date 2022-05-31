package org.sort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyFileReader {

    private final String fullFileName;
    private final Dictionary dictionary;

    public MyFileReader(String fullFileName, Dictionary dictionary) {
        if ((fullFileName == null) || (fullFileName.isEmpty())) {
            throw new IllegalArgumentException("File name cannot be null");
        }

        if (dictionary == null) {
            throw new IllegalArgumentException("Dictionary cannot be null");
        }

        this.fullFileName = fullFileName;
        this.dictionary = dictionary;
    }

    public void readFile() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fullFileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        saveWordsInDictionary(lines);
    }

    /**
     * Saves <i>words</i> into the {@link Dictionary}.
     * <p><b>Examples of <i>words</i>:</b></p>
     * boo,
     * boo-boo,
     * 404,
     * boo-007,
     * 404boo,
     * b,
     * -15.
     * <p><b>Examples of '<i>not-words</i>':</b></p>
     * boo!,
     * b+o*o,
     * -boo,
     * boo№007,
     * b o_o,
     * 1000-7.
     *
     * @param lines List of lines read from input file
     */
    private void saveWordsInDictionary(List<String> lines) {
        char[] wordChars;
        boolean hasLetters;
        char c;
        int lastIndexOfHyphen;
        int wordLength;
        String tempWord;

        for (String line : lines) {
            for (String word : line.split(" +")) {
                if (word.isEmpty()) {
                    continue;
                }

                tempWord = "";
                wordChars = word.toCharArray();
                hasLetters = false;
                lastIndexOfHyphen = word.lastIndexOf('-');
                wordLength = word.length();

                for (int i = 0; i < wordLength; i++) {
                    c = wordChars[i];

                    if (Character.isLetterOrDigit(c)
                            || ((c == '-') && (i != 0) && (i != wordLength - 1))
                            || (lastIndexOfHyphen == 0)) {
                        tempWord = ((c == '-') && !hasLetters && !tempWord.isEmpty())
                                ? saveAndResetIfPresents(tempWord)
                                : tempWord.concat(String.valueOf(c));
                        hasLetters = Character.isLetter(c) || hasLetters;
                    } else if (i < wordLength - 1) {
                        tempWord = saveAndResetIfPresents(tempWord);
                    }
                }

                saveAndResetIfPresents(tempWord);
            }
        }
    }

    private String saveAndResetIfPresents(String word) {
        if (!word.isEmpty()) {
            dictionary.addToDictionary(word);
            return  "";
        }

        return word;
    }
}