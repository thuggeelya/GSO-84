package org.statistics;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class CharStatisticsCollector implements ICharStatisticsCollector {

    private final Map<Character, CharCount> map = new HashMap<>();

    public Map<Character, CharCount> getMap() {
        return map;
    }

    @Override
    public void collect(Character ch) {
        if (!ch.equals('\n')) {
            CharCount value = map.putIfAbsent(ch, new CharCount(ch, new AtomicLong(1)));

            if (value != null) {
                value.getCount().incrementAndGet();
            }
        }
    }

    @Override
    public List<CharCount> getValues() {
        List<CharCount> values = new ArrayList<>(map.values());
        values.sort(Comparator.naturalOrder());
        return values;
    }

    @Override
    public String toString() {
        return "CharStatisticsCollector" + "{" + "map=" + map + '}';
    }
}