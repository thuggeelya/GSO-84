package org.statistics;

import java.util.List;
import java.util.Map;

public interface ICharStatisticsCollector {

    void collect(Character ch);

    List<CharCount> getValues();

    Map<Character, CharCount> getMap();
}