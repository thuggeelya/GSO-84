package org.example;

import java.util.Set;

public interface ICharStatisticsCollector {

    void collect(Character ch);

    Set<CharCount> packOutput();
}