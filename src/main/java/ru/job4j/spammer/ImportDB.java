package ru.job4j.spammer;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines()
                    .map(this::validate)
                    .map(s -> new User(s[0], s[1]))
                    .forEach(users::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    private String[] validate(String pair) {
        String[] lines = pair.split(";", 2);
        if (pair.startsWith(";")) {
            throw new IllegalArgumentException(
                    String.format("Error: This pair '%s' does not contains name or name and email", pair));
        }
        if (lines[1].isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("Error: This pair '%s' does not contains email", pair));
        }
        return lines;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("INSERT INTO users(name, email) VALUES (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "data/dump.txt");
        db.save(db.load());
    }
}
