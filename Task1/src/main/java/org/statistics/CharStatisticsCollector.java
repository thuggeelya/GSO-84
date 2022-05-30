package org.statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class CharStatisticsCollector implements ICharStatisticsCollector {

    private final Map<Character, CharCount> map = new HashMap<>();

    public Map<Character, CharCount> getMap() {
        return map;
    }

    @Override
    public void collect(Character ch) {
        if (!ch.equals('\n')) {
            map
                    .computeIfAbsent(ch, key -> new CharCount(key, new AtomicLong(0)))
                    .getCount()
                    .incrementAndGet();
        }
    }

    @Override
    public List<CharCount> getValues() {
        return map.values().stream().sorted().collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "CharStatisticsCollector" + "{" + "map=" + map + '}';
    }
}