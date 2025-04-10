package org.example;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Connector {
    private final Connection connection;
    private final QueryRunner run = new QueryRunner();

    public enum QueryMethod {
        SELECT,
        UPDATE,
        INSERT,
        DELETE,
        SCALAR,
        SELECT_DISPLAY_TEST;
    }

    public Connector(String connectionString) throws SQLException {
        this.connection = DriverManager.getConnection(connectionString);
    }

    private void selectAndDisplayAsArrayList(String query) throws SQLException {
        ResultSetHandler<List<Object[]>> handler = rs -> {
            ResultSetMetaData meta = rs.getMetaData();
            List<Object[]> result = new ArrayList<>();

            int cols = meta.getColumnCount();
            while (rs.next()) {
                Object[] obj = new Object[cols];
                for (int i = 0; i < cols; i++) {
                    obj[i] = rs.getObject(i + 1);
                }
                result.add(obj);
            }

            return result;
        };

        List<Object[]> result = run.query(connection, query, handler);
        for (Object[] row : result) {
            for (Object obj : row) {
                System.out.print(obj + "\t\t");
            }
            System.out.println();
        }
    }

    private void selectAndDisplayScalar(String query) throws SQLException {
        ScalarHandler<Integer> scalarHandler = new ScalarHandler<>();
        int s = run.query(connection, query, scalarHandler);
        System.out.println(s);
    }

    public void runQuery(String query, QueryMethod queryMethod) throws SQLException {
        switch (queryMethod) {
            case SELECT_DISPLAY_TEST:
                if (query.toUpperCase().contains("SELECT")) selectAndDisplayAsArrayList(query);
                else System.err.println("There is no SELECT in query");
                break;
            case SCALAR:
                selectAndDisplayScalar(query);
                break;
            default:
                System.err.println("Wrong query method");
        }
    }

    public void closeConnection() {
        DbUtils.closeQuietly(connection);
    }
}
