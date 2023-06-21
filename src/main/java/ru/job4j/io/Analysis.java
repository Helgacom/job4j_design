package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        var worked = true;
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(target)))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] lines = line.split(" ", 2);
                boolean error = "400".equals(lines[0]) || "500".equals(lines[0]);
                if (worked && error) {
                    worked = false;
                    out.write(lines[1] + ";");
                } else {
                    if (!worked && !error) {
                        worked = true;
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
