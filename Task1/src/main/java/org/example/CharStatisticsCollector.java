package org.example;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class CharStatisticsCollector implements ICharStatisticsCollector {

    private final Map<Character, CharCount> map = new TreeMap<>();

    public Map<Character, CharCount> getMap() {
        return map;
    }

    @Override
    public void collect(Character ch) {
        if (!ch.equals('\n')) {
            map.put(ch, map.getOrDefault(ch, new CharCount(ch, new AtomicLong(0))).incCountAndGet());
        }
    }

    @Override
    public List<CharCount> getValues() {
        return map.values().stream().sorted().collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o == null) || (getClass() != o.getClass())) return false;
        CharStatisticsCollector that = (CharStatisticsCollector) o;
        return getValues().equals(that.getValues());
    }

    @Override
    public int hashCode() {
        return Objects.hash(map.size());
    }

    @Override
    public String toString() {
        return "CharStatisticsCollector" + hashCode() + "{" +
                "map=" + map +
                '}';
    }
}