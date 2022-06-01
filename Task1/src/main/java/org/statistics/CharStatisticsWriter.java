package org.statistics;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.logging.Logger;

public class CharStatisticsWriter {

    private final String fullOutputFileName;
    private final List<CharCount> charCountList;

    public CharStatisticsWriter(List<CharCount> charCountList, String fullOutputFileName) {
        this.charCountList = charCountList;
        this.fullOutputFileName = fullOutputFileName;
    }

    public void writeCharStatistics() {
        long totalCharsCount = 0;

        for (CharCount charCount : charCountList) {
            totalCharsCount += charCount.getCount().get();
        }

        try (FileWriter writer = new FileWriter(fullOutputFileName)) {
            for (CharCount charCount : charCountList) {
                long count = charCount.getCount().get();
                double percentage = BigDecimal.valueOf(100d * count / totalCharsCount).setScale(1, RoundingMode.HALF_UP).doubleValue();
                writer.write("'" + charCount.getCh() + "'" + "(" + percentage + "%): " + "#".repeat((int) count) + System.lineSeparator());
            }
        } catch (IOException e) {
            Logger.getGlobal().severe(e.getMessage());
        }

    }
}