package ru.job4j.io.searcher;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class FileVisitor extends SimpleFileVisitor<Path> {
    private final Predicate<Path> condition;
    private final List<Path> lines = new ArrayList<>();

    public List<Path> getLines() {
        return lines;
    }

    public FileVisitor(Predicate<Path> condition) {
        this.condition = condition;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(file)) {
            lines.add(file.toAbsolutePath());
        }
        return CONTINUE;
    }
}
