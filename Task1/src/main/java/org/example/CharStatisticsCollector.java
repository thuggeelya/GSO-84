package org.example;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;

public class CharStatisticsCollector implements ICharStatisticsCollector {

    private final Map<Character, CharCount> map = new TreeMap<>();

    @Override
    public void collect(Character ch) {
        map.put(ch, map.getOrDefault(ch, new CharCount(ch, new AtomicLong(1))).incCountAndGet());
    }

    @Override
    public TreeSet<CharCount> packOutput() {
        return new TreeSet<>(map.values());
    }
}