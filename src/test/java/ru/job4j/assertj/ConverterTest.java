package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.assertj.Converter.convertSortedArray;

class ConverterTest {

    @Test
    void checkConvert() {
        int[] nums = new int[]{-14, -7, -2, 0, 9, 13, 18, 29};
        int[] rsl = convertSortedArray(nums);
        assertThat(rsl).hasSize(8)
                .containsExactly(0, 4, 49, 81, 169, 196, 324, 841);
    }
}