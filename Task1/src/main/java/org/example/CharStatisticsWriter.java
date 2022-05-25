package org.example;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class CharStatisticsWriter {

    private final String fullOutputFileName;
    private final List<CharCount> charCountSet;

    public CharStatisticsWriter(List<CharCount> charCountSet, String fullOutputFileName) {
        this.charCountSet = charCountSet;
        this.fullOutputFileName = fullOutputFileName;
    }

    public void writeCharStatistics() {
        List<String> lines = new ArrayList<>();
        AtomicLong charCount = new AtomicLong(0);
        charCountSet.forEach(c -> charCount.addAndGet(c.getCount().get()));
        charCountSet.stream().filter(c -> !c.getCh().equals('\n')).forEach(c -> {
            long count = c.getCount().get();
            double percentage = BigDecimal.valueOf(100d * count / charCount.get()).setScale(1, RoundingMode.HALF_UP).doubleValue();
            String sb = "'" + c + "'" + "(" + percentage + "%): " + "#".repeat((int) count);
            lines.add(sb);
        });

        try {
            Files.write(Path.of(this.fullOutputFileName), lines);
        } catch (IOException e) {
            Logger.getGlobal().severe("Something went wrong: " + e);
            e.printStackTrace();
        }
    }
}