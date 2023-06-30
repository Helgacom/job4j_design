package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> map = new HashMap<>();

    public void printRsl(Set<Path> rsl, FileProperty fileProperty) {
        if (rsl.size() > 0) {
            System.out.println(fileProperty.getName() + " ; " + fileProperty.getSize());
            for (Path f : rsl) {
                System.out.println(f);
            }
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        var fileProperty = new FileProperty(attrs.size(), file.toFile().getName());
        map.putIfAbsent(fileProperty, new LinkedList<>());
        map.get(fileProperty).add(file.toAbsolutePath());
        Set<Path> rsl = map.keySet().stream()
                        .filter(f -> f.equals(fileProperty) && map.get(f).size() > 1)
                        .flatMap(f -> map.get(f).stream())
                        .collect(Collectors.toSet());
        printRsl(rsl, fileProperty);
        return super.visitFile(file, attrs);
    }
}
