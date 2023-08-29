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

    public void createTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("CREATE TABLE IF NOT EXISTS %s();", tableName);
            statement.execute(sql);
        }
    }

    public void dropTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("DROP TABLE IF EXISTS %s;", tableName);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("ALTER TABLE %s ADD COLUMN %s %s;", tableName, columnName, type);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("ALTER TABLE %s DROP COLUMN %s CASCADE;", tableName, columnName);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName);
            statement.execute(sql);
        }
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
        tableEditor.createTable("test");
        System.out.println(tableEditor.getTableScheme("test"));
        tableEditor.addColumn("test", "id", "SERIAL PRIMARY KEY");
        tableEditor.addColumn("test", "Name", "TEXT");
        tableEditor.addColumn("test", "Contacts", "TEXT");
        System.out.println(tableEditor.getTableScheme("test"));
        tableEditor.dropColumn("test", "Name");
        System.out.println(tableEditor.getTableScheme("test"));
        tableEditor.renameColumn("test", "Contacts", "Address");
        System.out.println(tableEditor.getTableScheme("test"));
        tableEditor.dropTable("test");
        System.out.println(tableEditor.getTableScheme("test"));
        tableEditor.close();
    }
}
