package net.marvk.fs.vatsim.map.commons.motd;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RangeTest {
    @Test
    void rangeConstructorFailureTest() {
        assertThrows(IllegalArgumentException.class, () -> new Range<LocalDate>(null, null));
    }

    @Test
    void containsWithIncomparableFails1() {
        final Range<Object> range1 = Range.builder().from(new Object()).build();
        assertThrows(ClassCastException.class, () -> range1.contains(new Object()));
    }

    @Test
    void containsWithIncomparableFails2() {
        final Range<Object> range1 = Range.builder().from(new Object()).build();
        assertThrows(ClassCastException.class, () -> range1.containsExclusive(new Object()));
    }

    @Test
    void localDateFromContainsTest() {
        final Range<LocalDate> from2000 = new Range<>(LocalDate.of(2000, 1, 1), null);

        assertTrue(from2000.contains(LocalDate.of(2000, 1, 2)));
        assertTrue(from2000.contains(LocalDate.of(9999, 1, 1)));
        assertTrue(from2000.contains(LocalDate.of(2000, 1, 1)));
        assertFalse(from2000.contains(LocalDate.of(1999, 12, 31)));
        assertFalse(from2000.contains(LocalDate.of(0000, 1, 1)));
    }

    @Test
    void localDateToContainsTest() {
        final Range<LocalDate> to2000 = new Range<>(null, LocalDate.of(2000, 1, 1));

        assertFalse(to2000.contains(LocalDate.of(2000, 1, 2)));
        assertFalse(to2000.contains(LocalDate.of(9999, 1, 1)));
        assertTrue(to2000.contains(LocalDate.of(2000, 1, 1)));
        assertTrue(to2000.contains(LocalDate.of(1999, 12, 31)));
        assertTrue(to2000.contains(LocalDate.of(0000, 1, 1)));
    }

    @Test
    void localDateFromContainsExclusiveTest() {
        final Range<LocalDate> from2000 = new Range<>(LocalDate.of(2000, 1, 1), null);

        assertTrue(from2000.containsExclusive(LocalDate.of(2000, 1, 2)));
        assertTrue(from2000.containsExclusive(LocalDate.of(9999, 1, 1)));
        assertFalse(from2000.containsExclusive(LocalDate.of(2000, 1, 1)));
        assertFalse(from2000.containsExclusive(LocalDate.of(1999, 12, 31)));
        assertFalse(from2000.containsExclusive(LocalDate.of(0000, 1, 1)));
    }

    @Test
    void localDateToContainsExclusiveTest() {
        final Range<LocalDate> from2000 = new Range<>(null, LocalDate.of(2000, 1, 1));

        assertFalse(from2000.containsExclusive(LocalDate.of(2000, 1, 2)));
        assertFalse(from2000.containsExclusive(LocalDate.of(9999, 1, 1)));
        assertFalse(from2000.containsExclusive(LocalDate.of(2000, 1, 1)));
        assertTrue(from2000.containsExclusive(LocalDate.of(1999, 12, 31)));
        assertTrue(from2000.containsExclusive(LocalDate.of(0000, 1, 1)));
    }

    @Test
    void localDateRangeContainsTest() {
        final Range<LocalDate> from2000To2009 = new Range<>(LocalDate.of(2000, 1, 1), LocalDate.of(2009, 12, 31));

        assertFalse(from2000To2009.contains(LocalDate.of(0000, 1, 1)));
        assertFalse(from2000To2009.contains(LocalDate.of(1999, 12, 31)));
        assertTrue(from2000To2009.contains(LocalDate.of(2000, 1, 1)));
        assertTrue(from2000To2009.contains(LocalDate.of(2000, 1, 2)));
        assertTrue(from2000To2009.contains(LocalDate.of(2005, 6, 15)));
        assertTrue(from2000To2009.contains(LocalDate.of(2009, 12, 30)));
        assertTrue(from2000To2009.contains(LocalDate.of(2009, 12, 31)));
        assertFalse(from2000To2009.contains(LocalDate.of(2010, 1, 1)));
        assertFalse(from2000To2009.contains(LocalDate.of(9999, 1, 1)));
    }

    @Test
    void localDateRangeContainsExclusiveTest() {
        final Range<LocalDate> from2000To2009 = new Range<>(LocalDate.of(2000, 1, 1), LocalDate.of(2009, 12, 31));

        assertFalse(from2000To2009.containsExclusive(LocalDate.of(0000, 1, 1)));
        assertFalse(from2000To2009.containsExclusive(LocalDate.of(1999, 12, 31)));
        assertFalse(from2000To2009.containsExclusive(LocalDate.of(2000, 1, 1)));
        assertTrue(from2000To2009.containsExclusive(LocalDate.of(2000, 1, 2)));
        assertTrue(from2000To2009.containsExclusive(LocalDate.of(2005, 6, 15)));
        assertTrue(from2000To2009.containsExclusive(LocalDate.of(2009, 12, 30)));
        assertFalse(from2000To2009.containsExclusive(LocalDate.of(2009, 12, 31)));
        assertFalse(from2000To2009.containsExclusive(LocalDate.of(2010, 1, 1)));
        assertFalse(from2000To2009.containsExclusive(LocalDate.of(9999, 1, 1)));
    }
}