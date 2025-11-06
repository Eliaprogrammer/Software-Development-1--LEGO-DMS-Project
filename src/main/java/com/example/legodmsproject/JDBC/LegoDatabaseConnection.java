package com.example.legodmsproject.JDBC;

import com.example.legodmsproject.ReleaseDate;

import java.sql.*;

public class LegoDatabaseConnection {

    public static Connection getConnection(String url) throws SQLException{

        String sqlQuery = "SELECT * FROM LegoSet";
        try{
            Connection connect = DriverManager.getConnection(url);
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);


            return connect;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }


}
