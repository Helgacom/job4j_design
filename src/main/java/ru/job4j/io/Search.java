package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
        validate(args);
    }

    public static void validate(String[] args) {
        if (!(args.length == 2)) {
            throw new IllegalArgumentException("Arguments or one of them not found");
        }
        File file = new File(args[0]);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        if (!(args[0]).startsWith(".")) {
            throw new IllegalArgumentException("Wrong start folder, use root folder");
        }
        if (!(args[1].startsWith(".")) || args[1].length() < 2) {
            throw new IllegalArgumentException("Wrong folder format");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getLines();
    }
}