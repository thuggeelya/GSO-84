package org.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;
import java.util.stream.Stream;

public interface FileReadable extends IStringRefactoring {

    default String[] readFile() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String inputFilePath = br.readLine();
            return readFile(inputFilePath);
        } catch (IOException e) {
            Logger.getGlobal().severe("Something went wrong: " + e);
        }

        return new String[0];
    }

    default String[] readFile(String inputFile) {
        if (isBlank(inputFile)) {
            throw new IllegalArgumentException("File name cannot be null");
        }

        StringBuilder sb = new StringBuilder();
        try (Stream<String> lines = Files.lines(Path.of(inputFile), StandardCharsets.UTF_8)) {
            lines.forEach(sb::append);
        } catch (IOException e) {
            Logger.getGlobal().severe("Something went wrong: " + e);
        }

        return sb.toString().split(" ");
    }
}