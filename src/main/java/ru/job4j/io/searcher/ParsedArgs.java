package ru.job4j.io.searcher;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ParsedArgs {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return values.get(key);
    }
    private String[] validate(String pair) {
        String[] lines = pair.split("=", 2);

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
        if (lines[1].isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("Error: This argument '%s' does not contain a value", pair));
        }
        return lines;
    }

    private void parse(String[] args) {
        values.putAll(Arrays.stream(args)
                .map(this::validate)
                .collect(Collectors.toMap(s -> s[0].substring(1), s -> s[1])));
    }

    public static ParsedArgs of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ParsedArgs names = new ParsedArgs();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        var jvm = ParsedArgs.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        var zip = ParsedArgs.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
