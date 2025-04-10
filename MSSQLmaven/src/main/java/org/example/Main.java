package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String connectionString =
                "jdbc:sqlserver://localhost:1433;" +
                        "database=PD_212;" +
                        "user=PHP;" +
                        "password=111;" +
                        "TrustServerCertificate=True;";
        String query =
                "SELECT " +
                        "FORMATMESSAGE(N'%s %s %s',last_name,first_name,middle_name) AS 'Студент', " +
                        "group_name AS 'Группа', " +
                        "direction_name AS 'Направление' " +
                        "FROM " +
                        "Students " +
                        "JOIN " +
                        "Groups ON ([group]=group_id) " +
                        "JOIN " +
                        "Directions ON (direction=direction_id)";

        Connector connector = new Connector(connectionString);
        connector.runQuery(query, Connector.QueryMethod.SELECT_DISPLAY_TEST);
        connector.runQuery("SELECT COUNT(*) FROM Students", Connector.QueryMethod.SCALAR);
        connector.closeConnection();
    }
}

