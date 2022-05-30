package org.sort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

public interface FileWritable extends IStringRefactoring {

    default void writeFile(String outputFileName, List<String> lines) {
        if (isNullOrEmpty(outputFileName)) {
            throw new IllegalArgumentException("File name cannot be null");
        }

        try {
            Files.write(Paths.get(outputFileName), lines);
        } catch (IOException e) {
            Logger.getGlobal().severe(e.getMessage());
        }
    }
}