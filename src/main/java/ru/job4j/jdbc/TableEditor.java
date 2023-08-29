package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        connection = getConnection(properties);
    }

    private static Connection getConnection(Properties config) throws Exception {
        Class.forName(config.getProperty("driver_class"));
        String url = config.getProperty("url");
        String login = config.getProperty("username");
        String password = config.getProperty("password");
        return DriverManager.getConnection(url, login, password);
    }

    private void tryStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String createTable(String tableName) {
        return String.format("CREATE TABLE IF NOT EXISTS %s();", tableName);
    }

    public static String dropTable(String tableName) {
        return String.format("DROP TABLE IF EXISTS %s;", tableName);
    }

    public static String addColumn(String tableName, String columnName, String type) {
        return String.format("ALTER TABLE %s ADD COLUMN %s %s;", tableName, columnName, type);
    }

    public static String dropColumn(String tableName, String columnName) {
        return String.format("ALTER TABLE %s DROP COLUMN %s CASCADE;", tableName, columnName);
    }

    public static String renameColumn(String tableName, String columnName, String newColumnName) {
        return String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        TableEditor tableEditor = new TableEditor(config);
        tableEditor.tryStatement(createTable("test"));
        System.out.println(tableEditor.getTableScheme("test"));
        tableEditor.tryStatement(addColumn("test", "id", "SERIAL PRIMARY KEY"));
        tableEditor.tryStatement(addColumn("test", "Name", "TEXT"));
        tableEditor.tryStatement(addColumn("test", "Contacts", "TEXT"));
        System.out.println(tableEditor.getTableScheme("test"));
        tableEditor.tryStatement(dropColumn("test", "Name"));
        System.out.println(tableEditor.getTableScheme("test"));
        tableEditor.tryStatement(renameColumn("test", "Contacts", "Address"));
        System.out.println(tableEditor.getTableScheme("test"));
        tableEditor.tryStatement(dropTable("test"));
        System.out.println(tableEditor.getTableScheme("test"));
        tableEditor.close();
    }
}
