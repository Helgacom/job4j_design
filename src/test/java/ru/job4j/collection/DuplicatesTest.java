package ru.job4j.collection;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.collection.Duplicates.*;

class DuplicatesTest {
    @Test
    void checkFindDups() {
        String str = "artttrsvm";
        Set<Character> rsl = findDups(str);
        assertThat(rsl).hasSize(2)
                .containsExactlyInAnyOrder('r', 't')
                .doesNotContain('a')
                .doesNotContain('m', 's', 'v');

    }
    @Test
    void checkGetDups() {
        String[] data = new String[]{"ab", "cb", "cc", "ab", "cc"};
        List<String> rsl = getDups(data);
        assertThat(rsl).hasSize(2)
                .containsExactly("cc", "ab")
                .doesNotContain("cb");
    }

    @Test
    void checkDelDups() {
        int[] nums = new int[]{1, 1, 7, 8, 9, 3, 8, 9};
        int[] rsl = gelDups(nums);
        assertThat(rsl).hasSize(5)
                .containsOnlyOnce(1, 8, 9);
    }

}