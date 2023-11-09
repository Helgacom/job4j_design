package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FoolTest {

    @Test
    void checkFizz() {
        assertThat(Fool.chooseFizzBuzz(9)).isEqualTo("Fizz");
    }

    @Test
    void checkBuzz() {
        assertThat(Fool.chooseFizzBuzz(10)).isEqualTo("Buzz");
    }

    @Test
    void checkFizzBuzz() {
        assertThat(Fool.chooseFizzBuzz(1)).isEqualTo("FizzBuzz");
    }

    @Test
    void checkNumberWhenNotFizzBuzz() {
        assertThat(Fool.chooseFizzBuzz(2)).isEqualTo("2");
    }
}
