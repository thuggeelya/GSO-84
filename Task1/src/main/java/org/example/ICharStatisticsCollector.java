package org.example;

import java.util.List;
import java.util.Set;

public interface ICharStatisticsCollector {

    void collect(Character ch);

    List<CharCount> getValues();
}