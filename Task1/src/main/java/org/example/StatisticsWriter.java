package org.example;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class StatisticsWriter {

    private final String fileBody;
    private final String fullOutputFileName;

    public StatisticsWriter(String fileBody, String fullOutputFileName) {
        this.fileBody = fileBody;
        this.fullOutputFileName = fullOutputFileName;
        writeCharStatistics();
    }

    public void writeCharStatistics() {
        List<String> lines = new ArrayList<>();

        collectSortedData().forEach((c, i) -> {
            double percentage = BigDecimal.valueOf(100d * i / fileBody.length()).setScale(1, RoundingMode.HALF_UP).doubleValue();
            String sb = "'" + c + "'" + "(" + percentage + "%): " + "#".repeat(i);
            lines.add(sb);
        });

        try {
            Files.write(Path.of(fullOutputFileName), lines);
        } catch (IOException e) {
            Logger.getGlobal().severe("Something went wrong: " + e);
        }
    }

    private Map<Character, Integer> collectSortedData() {
        Map<Character, Integer> statsMap = new HashMap<>();

        for (char c : fileBody.toCharArray()) {
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
}