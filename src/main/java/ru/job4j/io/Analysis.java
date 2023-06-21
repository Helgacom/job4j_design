package ru.job4j.io;

import java.io.*;
import java.util.Arrays;

public class Analysis {
    public void unavailable(String source, String target) {
        var worked = true;
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(target)))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if (worked && (line.startsWith("400") || line.startsWith("500"))) {
                    worked = false;
                    String[] lines = line.split(" ", 2);
                    out.write(lines[1] + ";");
                } else {
                    if (!worked && (line.startsWith("200") || line.startsWith("300"))) {
                        worked = true;
                        String[] lines = line.split(" ", 2);
                        out.write(lines[1] + ";" + System.lineSeparator());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
