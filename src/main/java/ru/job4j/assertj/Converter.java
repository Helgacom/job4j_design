package ru.job4j.assertj;

public class Converter {

    public static int[] convertSortedArray(int[] data) {
        int[] rsl = new int[data.length];
        int left = 0;
        int right = data.length - 1;
        for (int i = data.length - 1; i >= 0; i--) {
            if (Math.abs(data[left]) < Math.abs(data[right])) {
                rsl[i] = data[right] * data[right];
                right--;
            } else {
                rsl[i] = data[left] * data[left];
                left++;
            }
        }
        return rsl;
    }
}
