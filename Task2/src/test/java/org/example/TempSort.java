package org.example;

import org.sort.IFileSortingStrategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TempSort {

    private static final String CHARS = "aqwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
    private Path tempFile;
    private IFileSortingStrategy strategy;
    private List<String> words;

    public Path getTempFile() {
        return tempFile;
    }

    public void setStrategy(IFileSortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void writeRandomText() throws IOException {
        char v;
        words = new ArrayList<>();
        StringBuilder line = new StringBuilder();
        StringBuilder word;

        for (int i = 0; i < 25; i++) {
            word = new StringBuilder();
            for (int j = 0; j < 4; j++) {
                v = CHARS.charAt((int) (Math.random() * CHARS.length()));
                line.append(v);
                word.append(v);
            }

            words.add(word.toString());
            line.append(' ');
        }

        Path tempFile = Files.createTempFile(Path.of("src/test/resources/"), "temp", ".txt");
        this.tempFile = tempFile;
        Files.write(tempFile, Collections.singleton(line.toString()));
    }

    public void sortTemp(String outputFile) {
        strategy.sort(words.toArray(new String[]{}), outputFile);
    }
}