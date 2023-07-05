package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        values.putAll(Arrays.stream(args)
                .map(line -> line.split("=", 2))
                .collect(Collectors.toMap(s -> s[0].substring(1), s -> s[1])));
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String pair : args) {
            if (pair.isEmpty()) {
                throw new IllegalArgumentException("Arguments not passed to program");
            }
            if (!pair.startsWith("-")) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not start with a '-' character", pair));
            }
            if (!pair.contains("=")) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not contain an equal sign", pair));
            }
            if (pair.startsWith("-=")) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not contain a key", pair));
            }
            if (pair.split("=", 2)[1].isEmpty()) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not contain a value", pair));
            }
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
