package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/path.txt", "data/botAnswers.txt");
        cc.run();
    }

    public void run() {
        List<String> log = new ArrayList<>();
        System.out.println("Привет! Давай пообщаемся) Согласен?");
        log.add("Привет! Давай пообщаемся) Согласен?");
        boolean run = true;
        while (run) {
            System.out.println("Введи команду: закончить, стоп или продолжить");
            log.add("Введи команду: закончить, стоп или продолжить");
            Scanner in = new Scanner(System.in);
            String message = in.nextLine();
            log.add(message);
            run = !message.equals(OUT);

            if (STOP.equals(message)) {
                String line = in.nextLine();
                log.add(line);
            }
            if (CONTINUE.equals(message)) {
                System.out.println("Спроси меня о чем-нибудь?");
                log.add("Спроси меня о чем-нибудь?");
                String line = in.nextLine();
                log.add(line);
                var answers = readPhrases();
                Random random = new Random();
                int index = random.nextInt(answers.size());
                System.out.println(answers.get(index));
                log.add(answers.get(index));
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            reader.lines().forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.stream().map(s -> s + System.lineSeparator()).forEach(writer::print);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
