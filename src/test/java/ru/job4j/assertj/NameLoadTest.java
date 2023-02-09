package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void whenNamesArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void whenNameIsNotContainsSymbol() {
        NameLoad nameLoad = new NameLoad();
        String name = "key:value";
        String[] names = {name, "key=value"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain the symbol \"=\"", name);
    }

    @Test
    void whenNameIsNotContainsKey() {
        NameLoad nameLoad = new NameLoad();
        String name = "=value";
        String[] names = {name, "key=value"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain a key", name);
    }

    @Test
    void whenNameIsNotContainsValue() {
        NameLoad nameLoad = new NameLoad();
        String name = "key=";
        String[] names = {name, "key=value"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain a value", name);
    }

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }
}