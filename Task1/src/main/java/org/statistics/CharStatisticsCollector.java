package org.statistics;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class CharStatisticsCollector implements ICharStatisticsCollector {

    private final Map<Character, CharCount> map = new HashMap<>();

    @Override
    public Map<Character, CharCount> getMap() {
        return map;
    }

    @Override
    public void collect(Character ch) {
        if (!ch.equals('\n')) {
            CharCount charCount = map.get(ch);

            if(charCount == null) {
                CharCount newCharCount = new CharCount(ch, new AtomicLong(0));
                charCount = map.putIfAbsent(ch, newCharCount);

                if (charCount == null) {
                    charCount = newCharCount;
                }
            }

            charCount.increment();
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