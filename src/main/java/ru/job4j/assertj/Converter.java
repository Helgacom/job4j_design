package ru.job4j.assertj;

public class Converter {

    public static int[] convert(int[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i] * data[i];
        }
        return data;
    }
}
