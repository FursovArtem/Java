package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String connectionString =
                "jdbc:sqlserver://192.168.0.236:1433;" +
                        "database=PD_212;" +
                        "user=PHP;" +
                        "password=111;"+
                        "Connect Timeout=30;Encrypt=True;" +
                        "TrustServerCertificate=True;";
        try
        {
            Connection connection = DriverManager.getConnection(connectionString);

            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
