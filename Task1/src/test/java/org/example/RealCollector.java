package org.example;

import org.statistics.CharCount;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public final class RealCollector {

    @SuppressWarnings("unused")
    public static final String INPUT = "The real collector";
    public static final Map<Character, CharCount> MAP = new HashMap<>();

    static {
        MAP.put('T', new CharCount('T', new AtomicLong(1)));
        MAP.put('a', new CharCount('a', new AtomicLong(1)));
        MAP.put('c', new CharCount('c', new AtomicLong(2)));
        MAP.put('e', new CharCount('e', new AtomicLong(3)));
        MAP.put('h', new CharCount('h', new AtomicLong(1)));
        MAP.put('l', new CharCount('l', new AtomicLong(3)));
        MAP.put('o', new CharCount('o', new AtomicLong(2)));
        MAP.put('r', new CharCount('r', new AtomicLong(2)));
        MAP.put('t', new CharCount('t', new AtomicLong(1)));
        MAP.put(' ', new CharCount(' ', new AtomicLong(2)));
    }
}