package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .contains("second", "four")
                .containsOnly("three", "first", "five", "second", "four")
                .containsExactly("first", "second", "three", "four", "five")
                .containsAnyOf("ten", "twelve", "four")
                .doesNotContain("ten", "twelve")
                .startsWith("first", "second")
                .endsWith("four", "five")
                .containsSequence("second", "three");
    }

    @Test
    void checkOneElementOfList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).first().isEqualTo("first")
                .isNotNull();
    }

    @Test
    void exampleToSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> list = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(list).isNotEmpty()
                .hasSize(5)
                .contains("second", "four")
                .containsOnly("three", "first", "five", "second", "four")
                .containsAnyOf("ten", "twelve", "four")
                .doesNotContainSequence("second", "three")
                .doesNotContain("ten", "twelve");
    }

    @Test
    void exampleToMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map).isNotEmpty()
                .hasSize(5)
                .containsKeys("first")
                .containsValues(1)
                .doesNotContainKey("six")
                .containsEntry("second", 1);
    }
}