package org.sort;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

public interface Sort {

    String COMMON_RESOURCES_PATH = "Task2/src/main/resources/";

    default String[] readFile(String name) {
        StringBuilder sb = new StringBuilder();
        String fullFileName = Paths.get(COMMON_RESOURCES_PATH + name).toAbsolutePath().toString();

        try(Stream<String> lines = Files.lines(Path.of(fullFileName), StandardCharsets.UTF_8)) {
            lines.forEach(sb::append);
        } catch (IOException e) {
            Logger.getGlobal().severe("Something went wrong: " + e);
        }

        return sb.toString().split(" ");
    }

    default void writeFile(String outputFileName, List<String> lines) {
        try {
            Files.write(Path.of(Paths.get(COMMON_RESOURCES_PATH + outputFileName).toAbsolutePath().toString()), lines);
        } catch (IOException e) {
            Logger.getGlobal().severe("Something went wrong: " + e);
        }
    }

    void sort(String[] text);

    default String onlyAlphabeticAndDigit(String s) {
        if ((s == null) || (s.isBlank())) {
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