package org.example;

import java.nio.file.Paths;
import java.util.Objects;

public class StatisticsGenerator {

    private static final String COMMON_RESOURCES_PATH = "Task1/src/main/resources/";
    private String fileName;
    private String outputFileName;
    private String fileBody;

    public StatisticsGenerator(String fileName, String outputFileName) {
        if (isBlank(fileName) || isBlank(outputFileName)) {
            throw new IllegalArgumentException("File name cannot be null");
        }

        setFileName(fileName);
        this.outputFileName = outputFileName;
    }

    public String getFileBody() {
        return this.fileBody;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        this.fileBody = this.readFile();
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    private String readFile() {
        return new FileReader(getFullFileName(this.fileName)).readFile();
    }

    public void writeCharStatistics() {
        new StatisticsWriter(this.fileBody, getFullFileName(this.outputFileName));
    }

    private String getFullFileName(String f) {
        return Paths.get(COMMON_RESOURCES_PATH + f).toAbsolutePath().toString();
    }

    private boolean isBlank(String s) {
        return (s == null) || (s.isBlank());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticsGenerator that = (StatisticsGenerator) o;
        return fileName.equals(that.fileName) && fileBody.equals(that.fileBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, fileBody);
    }

    @Override
    public String toString() {
        return this.fileBody;
    }
}