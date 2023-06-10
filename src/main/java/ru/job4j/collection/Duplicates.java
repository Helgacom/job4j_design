package ru.job4j.collection;

import java.util.*;
import java.util.stream.Collectors;

public class Duplicates {

    public static Set<Character> findDups(String str) {
        Set<Character> rsl = new HashSet<>();
        Set<Character> chars = new HashSet<>();
        for (char el : str.toCharArray()) {
            if (!chars.add(el)) {
                rsl.add(el);
            }
        }
        return rsl;
    }

    public static List<String> getDups(String[] data) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : data) {
            if (!map.containsKey(str)) {
                map.put(str, 1);
            } else {
                map.put(str, map.get(str) + 1);
            }
        }
        List<String> duplicates = map.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return duplicates;
    }

    public static int[] gelDups(int[] nums) {
        return Arrays.stream(nums).distinct().toArray();
    }
}
