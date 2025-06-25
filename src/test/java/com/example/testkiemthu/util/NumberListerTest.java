package com.example.testkiemthu.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NumberListerTest {

    private final List<Integer> numbers = NumberLister.listNumbersSkippingMultiplesOfFive();

    @Test
    @DisplayName("Should contain 1 as lower boundary")
    void testContainsLowerBoundary() {
        assertThat(numbers).contains(1);
    }

    @Test
    @DisplayName("Should not contain 5 which is divisible by 5 (equivalence class of excluded numbers)")
    void testSkipMultipleOfFive() {
        assertThat(numbers).doesNotContain(5, 10, 100);
    }

    @Test
    @DisplayName("Should include 99 as upper boundary - 100 excluded")
    void testUpperBoundary() {
        assertThat(numbers).contains(99);
        assertThat(numbers).doesNotContain(100);
    }

    @Test
    @DisplayName("Size must be 80 (100 total minus 20 multiples of 5)")
    void testSize() {
        assertThat(numbers).hasSize(80);
    }

    @Test
    @DisplayName("Sequence should start at 1 and next after 4 is 6 (border near skipped value)")
    void testSequenceAroundSkippedValue() {
        int indexOf4 = numbers.indexOf(4);
        assertThat(numbers.get(indexOf4 + 1)).isEqualTo(6);
    }
} 