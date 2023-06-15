package ru.job4j.io;

import java.io.FileOutputStream;
import java.util.Arrays;

public class MultipleFile {
    public static int[][] multiple(int size) {
        int[][] rsl = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rsl[i][j] = (i + 1) * (j + 1);
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/multipleresult.txt")) {
            int[][] rsl = multiple(9);
            for (int i = 0; i < rsl.length; i++) {
                out.write(Arrays.toString(rsl[i]).getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
