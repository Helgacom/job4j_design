package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;

@Disabled
class StringsGeneratorTest {

    @Test
    void checkValidGenerator() {
        String template = "I am a ${name}, Who are ${subject}?";
        var args = new HashMap<String, String>();
        args.put("name", "Ivan Ivanov");
        args.put("subject", "you");
        Generator generator = new StringsGenerator();
        assertThat(generator.produce(template, args)).isEqualTo("I am a Ivan Ivanov, Who are you?");
    }

    @Test
    void checkGeneratorWhenMapNotContainsKey() {
        String template = "I am a ${name}, Who are ${subject}?";
        var args = new HashMap<String, String>();
        args.put("name", "Ivan Ivanov");
        Generator generator = new StringsGenerator();
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("This key not found");
    }

    @Test
    void checkGeneratorWhenMapContainsUnnecessaryKey() {
        String template = "I am a ${name}, Who are ${subject}?";
        var args = new HashMap<String, String>();
        args.put("name", "Ivan Ivanov");
        args.put("subject", "you");
        args.put("also", "UnnecessaryKey");
        Generator generator = new StringsGenerator();
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Map contains unnecessary key");
    }


}