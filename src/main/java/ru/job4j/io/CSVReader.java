package ru.job4j.io;

import java.io.*;
import java.util.Scanner;

public class CSVReader {
    public static void main(String[] args) throws Exception {
        validate(args);
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }

    public static void handle(ArgsName argsName) {
        var delimiter = argsName.get("delimiter");
        var out = argsName.get("out");
        var sb = new StringBuilder();

        try (var scanner = new Scanner(new BufferedReader(new FileReader(argsName.get("path"))))
                .useDelimiter(System.lineSeparator())) {
            String header = scanner.nextLine();
            String[] headers = header.split(delimiter, 4);
            int[] indexes = findFilterIndex(argsName, headers);
            for (int index : indexes) {
                sb.append(headers[index]).append(delimiter);
            }
            sb.deleteCharAt(sb.length() - 1).append(System.lineSeparator());
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(delimiter, 4);
                for (int index : indexes) {
                    sb.append(data[index]).append(delimiter);
                }
                sb.deleteCharAt(sb.length() - 1).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if ("stdout".equals(out)) {
            System.out.println(sb);
        } else {
            try (PrintWriter printWriter = new PrintWriter(out)) {
                printWriter.print(sb);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int[] findFilterIndex(ArgsName argsName, String[] columns) {
        String[] filters = argsName.get("filter").split(",");
        int[] indexes = new int[filters.length];
        for (int i = 0; i < filters.length; i++) {
            for (int j = 0; j < columns.length; j++) {
                if (filters[i].equals(columns[j])) {
                    indexes[i] = j;
                    break;
                }
            }
        }
        return indexes;
    }

    private static void validate(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Arguments or one of them not found");
        }
        if (!(args[0]).endsWith(".csv")) {
            throw new IllegalArgumentException("Wrong file format");
        }
        if (args[1].split("=")[1].isEmpty()) {
            throw new IllegalArgumentException("Wrong delimiter argument");
        }
        String filters = args[3].split("=", 2)[1];
        if (filters.isBlank()) {
            throw new IllegalArgumentException("Empty filter argument");
        }
    }
}
