package org.example;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class MyFileReader {

    private static final String COMMON_RESOURCES_PATH = "Task1/src/main/resources/";
    private String fileName;
    private String outputFileName;
    protected String fileBody;

    public MyFileReader(String fileName, String outputFileName) {
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
        StringBuilder sb = new StringBuilder();
        String fullFileName = getFullFileName(this.fileName);

        try {
            Files.readAllLines(Path.of(fullFileName), StandardCharsets.UTF_8).forEach(sb::append);
        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println("Something went wrong..");
        }

        return sb.toString();
    }

    private String getFullFileName(String f) {
        return Paths.get(COMMON_RESOURCES_PATH + f).toAbsolutePath().toString();
    }

    public void writeCharStatistics() {
        List<String> lines = new ArrayList<>();

        collectSortedData().forEach((c, i) -> {
            double percentage = BigDecimal.valueOf(100d * i / this.fileBody.length()).setScale(1, RoundingMode.HALF_UP).doubleValue();
            String sb = "'" + c + "'" + "(" + percentage + "%): " + "#".repeat(i);
            lines.add(sb);
        });

        try {
            Files.write(Path.of(getFullFileName(this.outputFileName)), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<Character, Integer> collectSortedData() {
        Map<Character, Integer> statsMap = new HashMap<>();

        for (char c : this.fileBody.toCharArray()) {
            if (statsMap.containsKey(c)) {
                statsMap.replace(c, statsMap.get(c) + 1);
            } else {
                statsMap.put(c, 1);
            }
        }

        return sortData(statsMap);
    }

    private Map<Character, Integer> sortData(Map<Character, Integer> m) {
        return m.entrySet()
                .stream()
                .sorted((e1, e2) -> {
                    if (e2.getValue() > e1.getValue()) {
                        return 1;
                    } else if (e1.getValue() > e2.getValue()) {
                        return -1;
                    } else {
                        return Character.compare(e1.getKey(), e2.getKey());
                    }
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyFileReader that = (MyFileReader) o;
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