package org.statistics;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class CharCount implements Comparable<CharCount> {

    private final Character ch;
    private final AtomicLong count;

    public CharCount(Character ch, AtomicLong count) {
        this.ch = ch;
        this.count = count;
    }

    public Character getCh() {
        return ch;
    }

    public long getCount() {
        return count.get();
    }

    public void increment() {
        count.incrementAndGet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharCount charCount = (CharCount) o;
        return ch.equals(charCount.ch) && count.get() == charCount.count.get();
    }

    @Override
    public int hashCode() {
        return Objects.hash(ch);
    }

    @Override
    public int compareTo(CharCount o) {
        int result = Long.compare(o.getCount(), getCount());
        return result != 0 ? result : Character.compare(ch, o.getCh());
    }

    @Override
    public String toString() {
        return ch.toString() + " - " + count.get();
    }
}