package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static ru.job4j.io.Search.search;

public class Zip {

    public static void main(String[] args) throws IOException {
        var argsName = validate(args);
        List<Path> sources = search(Path.of(argsName.get("d")), p -> !p.toFile().getName().endsWith(argsName.get("e")));

        Zip zip = new Zip();
        zip.packFiles(sources, new File(argsName.get("o")));

        Zip zip1 = new Zip();
        zip1.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }

    public void packFiles(List<Path> sources, File target) {
        for (Path source: sources) {
            try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                    zip.closeEntry();
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArgsName validate(String[] args) {
        if (!(args.length == 3)) {
            throw new IllegalArgumentException("Arguments or one of them not found");
        }
        ArgsName argsName = ArgsName.of(args);
        File file = new File(argsName.get("d"));
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        if (!(argsName.get("e")).startsWith(".")) {
            throw new IllegalArgumentException("Wrong format");
        }
        if (!(argsName.get("o").endsWith(".zip"))) {
            throw new IllegalArgumentException("Wrong folder format");
        }
        return argsName;
    }
}
