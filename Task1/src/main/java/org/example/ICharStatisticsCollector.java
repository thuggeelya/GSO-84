package org.example;

import java.util.List;

public interface ICharStatisticsCollector {

    void collect(Character ch);

    List<CharCount> getValues();
}