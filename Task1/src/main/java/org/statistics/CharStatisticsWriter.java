package org.statistics;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        List<String> lines = new ArrayList<>();
        long charsNumber = 0;

        for (CharCount charCount : charCountList) {
            charsNumber += charCount.getCount().get();
        }

        for (CharCount charCount : charCountList) {
            long count = charCount.getCount().get();
            double percentage = BigDecimal.valueOf(100d * count / charsNumber).setScale(1, RoundingMode.HALF_UP).doubleValue();
            lines.add("'" + charCount.getCh() + "'" + "(" + percentage + "%): " + "#".repeat((int) count));
        }

        try {
            Files.write(Paths.get(fullOutputFileName), lines);
        } catch (IOException e) {
            Logger.getGlobal().severe(e.getMessage());
        }
    }
}