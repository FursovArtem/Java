package com.github.fursovartem.academymvc;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static Connection connection;
    private static String connectionString = "jdbc:sqlserver://localhost:1433;" +
            "database=PD_212;" +
            "user=PHP;" +
            "password=111;" +
            "TrustServerCertificate=True;";

    public Connector(String connectionString) {
        try {
            connection = DriverManager.getConnection(connectionString);
            Connector.connectionString = connectionString;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connector() {
        try {
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static String getConnectionString() { return connectionString; }

    public static void setConnectionString(String connectionString) {
        Connector.connectionString = connectionString;
    }

    public void closeConnection() {
        DbUtils.closeQuietly(connection);
    }
}
