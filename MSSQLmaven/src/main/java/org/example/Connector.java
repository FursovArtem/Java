package org.example;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.*;

public class Connector {
    String connectionString;
    Connection connection;
    public Connector(String connectionString)
    {
        this.connectionString = connectionString;
        try
        {
            connection = DriverManager.getConnection(connectionString);
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
    public int Scalar(String query)
    {
        int scalar=-1;
        try(Statement statement = connection.createStatement())
        {
            scalar = statement.executeUpdate(query);
            System.out.println(scalar);
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return scalar;
    }
    public void Select(String query)
    {
        try
        {
            Statement statement = connection.createStatement();//Открывает соединение??????/

            ResultSet results = statement.executeQuery(query);
            ResultSetMetaData metaData = results.getMetaData();
            for(int i=1; i <= metaData.getColumnCount(); i++)
            {
                System.out.println(metaData.getColumnName(i) + "\t\t" + metaData.getColumnClassName(i));
            }
            System.out.println();
            while (results.next())
            {
               /* byte id = results.getByte("direction_id");
                String name = results.getString("direction_name");
                System.out.println(id+"\t\t"+name);*/
                for(int i=1; i<=metaData.getColumnCount(); i++)
                {
                    System.out.print(results.getObject(i) + "\t");
                }
                System.out.println();
            }
            results.close();
            //connection.close(); //Если закрыть, то последующие вызовы Select() НЕ будут работать
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
