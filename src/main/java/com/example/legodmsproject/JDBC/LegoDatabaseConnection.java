package com.example.legodmsproject.JDBC;

import java.sql.*;

/**
 * Elia Schwartz CEN 3024C - 14877 Software Development
 * November 10, 2025,
 * LegoDatabaseConnection.java
 * Purpose: Tests the connection to the SQLite LEGO Collection Database
 */
public class LegoDatabaseConnection {

    /**
     * Print out information if the database has connectivity
     * @param url - The database url path (where the database is located on the computer)
     * @return The variable "connect" that holds the DriverManager
     * @throws SQLException - An error occurs when there is a problem with the SQL command
     */
    public static Connection getConnection(String url) throws SQLException{

        String sqlQuery = "SELECT * FROM LegoSet";
        try{
            Connection connect = DriverManager.getConnection(url);
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()){
                for(int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++){
                    System.out.println(resultSet.getString(i));
                }
            }
            return connect;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
