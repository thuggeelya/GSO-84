package org.sort;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.stream.Stream;

public interface FileReadable extends IStringRefactoring {

    default String[] readFile(String inputFile) {
        if (isNullOrEmpty(inputFile)) {
            throw new IllegalArgumentException("File name cannot be null");
        }

        StringBuilder sb = new StringBuilder();
        try (Stream<String> lines = Files.lines(Paths.get(inputFile), StandardCharsets.UTF_8)) {
            lines.forEach(sb::append);
        } catch (IOException e) {
            Logger.getGlobal().severe(e.getMessage());
        }

        return sb.toString().split(" ");
    }
}