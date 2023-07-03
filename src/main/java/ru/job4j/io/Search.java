package ru.job4j.io;

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
    }

    public static boolean validate(Path path) {
        if (!path.startsWith(".")) {
            throw new IllegalArgumentException("Wrong start folder, use root folder");
        }
        if (path.getFileName().toString().split(".").length == 2) {
            throw new IllegalArgumentException("Wrong folder format");
        }
        return true;
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        validate(root);
        Files.walkFileTree(root, searcher);
        return searcher.getLines();
    }
}