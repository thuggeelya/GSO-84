package org.sort;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

public interface Sort {

    String COMMON_RESOURCES_PATH = "Task2/src/main/resources/";

    default String[] readFile(String name) {
        if (isBlank(name)) {
            throw new IllegalArgumentException("File name cannot be null");
        }

        StringBuilder sb = new StringBuilder();

        try(Stream<String> lines = Files.lines(Path.of(getFullPath(name)), StandardCharsets.UTF_8)) {
            lines.forEach(sb::append);
        } catch (IOException e) {
            Logger.getGlobal().severe("Something went wrong: " + e);
        }

        return sb.toString().split(" ");
    }

    default void writeFile(String outputFileName, List<String> lines) {
        if (isBlank(outputFileName)) {
            throw new IllegalArgumentException("File name cannot be null");
        }

        try {
            Files.write(Path.of(getFullPath(outputFileName)), lines);
        } catch (IOException e) {
            Logger.getGlobal().severe("Something went wrong: " + e);
        }
    }

    private String getFullPath(String p) {
        return Paths.get(COMMON_RESOURCES_PATH + p).toAbsolutePath().toString();
    }
    void sort(String[] text);

    default String onlyAlphabeticAndDigit(String s) {
        if (isBlank(s)) {
            return s;
        }

        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (Character.isAlphabetic(c) || Character.isDigit(c)) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    default boolean isBlank(String s) {
        return (s == null) || (s.isBlank());
    }
}