package org.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

public interface FileWritable extends IStringRefactoring {

    default void writeFile(String outputFileName, List<String> lines) {
        if (isBlank(outputFileName)) {
            throw new IllegalArgumentException("File name cannot be null");
        }

        try {
            Files.write(Path.of(outputFileName), lines);
        } catch (IOException e) {
            Logger.getGlobal().severe("Something went wrong: " + e);
        }
    }

    default void writeFile(List<String> lines) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String outputFilePath = br.readLine();
            writeFile(outputFilePath, lines);
        } catch (IOException e) {
            Logger.getGlobal().severe("Something went wrong: " + e);
            e.printStackTrace();
        }
    }
}