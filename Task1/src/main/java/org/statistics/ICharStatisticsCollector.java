package org.statistics;

import java.util.List;

public interface ICharStatisticsCollector {

    void collect(Character ch);

    List<CharCount> getValues();
}