package ru.job4j.io.searcher;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Finder {
    public static void main(String[] args) throws IOException {
        validate(args);
        ParsedArgs argsNames = ParsedArgs.of(args);
        Path start = Paths.get(argsNames.get("d"));
        List<Path> rsl = search(start, path -> path.toString().matches(getPattern(argsNames)));
        writeFile(rsl, argsNames.get("o"));
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        FileVisitor fileVisitor = new FileVisitor(condition);
        Files.walkFileTree(root, fileVisitor);
        return fileVisitor.getLines();
    }

    public static void writeFile(List<Path> paths, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Path path : paths) {
                writer.write(path.toString());
                writer.write(System.lineSeparator());
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getPattern(ParsedArgs argsNames) {
        String pattern = argsNames.get("n");
        if ("mask".equals(argsNames.get("t"))) {
            pattern = pattern
                    .replace("?", ".?")
                    .replace("*", ".*");
        }
        return pattern;
    }

    private static void validate(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Arguments or one of them not found");
        }
        if (!args[0].startsWith("-d")) {
            throw new IllegalArgumentException("First argument must contain '-d' - start direction");
        }
        if (!args[1].startsWith("-n")) {
            throw new IllegalArgumentException("Second argument must contain '-n' - file name, mask or regex");
        }
        if (!args[2].startsWith("-t")) {
            throw new IllegalArgumentException("Third argument must contain '-t' - search criterion");
        }
        if (!args[3].startsWith("-o")) {
            throw new IllegalArgumentException("Fourth argument must contain '-o' - file name for recording result");
        }
    }
}

