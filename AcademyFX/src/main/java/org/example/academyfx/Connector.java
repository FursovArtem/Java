package org.example.academyfx;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private final Connection connection;
    private final String connectionString;

    public Connection getConnection() {
        return connection;
    }

    public String getConnectionString() {
        return connectionString;
    }

    Connector(String connectionString) throws SQLException {
        this.connection = DriverManager.getConnection(connectionString);
        this.connectionString = connectionString;
    }

    public void closeConnection() {
        DbUtils.closeQuietly(connection);
    }
}
