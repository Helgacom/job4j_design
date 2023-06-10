package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.assertj.Converter.convert;

class ConverterTest {

    @Test
    void checkConvert() {
        int[] nums = new int[]{-4, 7, 0, 9, 3, -8, 9};
        int[] rsl = convert(nums);
        assertThat(rsl).hasSize(7)
                .containsExactly(16, 49, 0, 81, 9, 64, 81);
    }

}