package com.example.testkiemthu.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class that provides common number-related helpers.
 */
public final class NumberLister {

    private NumberLister() {
        // utility class
    }

    /**
     * Returns a list of integers from 1 to 100 (inclusive) while skipping every
     * number that is divisible by 5.
     *
     * @return list containing 1..100 excluding multiples of 5
     */
    public static List<Integer> listNumbersSkippingMultiplesOfFive() {
        List<Integer> result = new ArrayList<>(80); // 100 - 20 multiples of 5
        for (int i = 1; i <= 100; i++) {
            if (i % 5 == 0) {
                continue; // skip multiples of 5
            }
            result.add(i);
        }
        return result;
    }
} 