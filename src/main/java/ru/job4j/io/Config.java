package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            values.putAll(in.lines()
                    .filter(this::validate)
                    .filter(line -> !line.startsWith("#") && !line.isBlank())
                    .map(line -> line.split("=", 2))
                    .collect(Collectors.toMap(s -> s[0], s -> s[1])));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    public boolean validate(String line) {
        if (line.startsWith("=")) {
            throw new IllegalArgumentException("find pairs with errors: line without key");
        }
        if (line.endsWith("=")) {
            throw new IllegalArgumentException("find pairs with errors: line without value");
        }
        if (!line.contains("=") && !line.isBlank()) {
            throw new IllegalArgumentException("find pairs with errors: line without =");
        }
        return true;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
