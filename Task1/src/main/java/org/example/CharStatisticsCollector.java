package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
            CharCount charCount = map.get(ch);

            if (charCount != null) {
                map.get(ch).getCount().incrementAndGet();
            } else {
                map.put(ch, new CharCount(ch, new AtomicLong(0)));
            }
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
        return Objects.hash(getClass());
    }

    @Override
    public String toString() {
        return "CharStatisticsCollector" + hashCode() + "{" +
                "map=" + map +
                '}';
    }
}