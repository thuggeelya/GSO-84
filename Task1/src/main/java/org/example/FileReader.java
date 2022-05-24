package org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class FileReader {

    private final String fullFileName;

    public FileReader(String fullFileName) {
        this.fullFileName = fullFileName;
    }

    public String readFile() {
        StringBuilder sb = new StringBuilder();

        try (Stream<String> lines = Files.lines(Path.of(fullFileName), StandardCharsets.UTF_8)) {
            lines.forEach(sb::append);
        } catch (IOException e) {
            Logger.getGlobal().severe("Something went wrong: " + e);
        }

        return sb.toString();
    }
}