package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtilSQLite {

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("problem occurred locating SQLite driver");
        }

        String url = "jdbc:sqlite:Phase0WithSQLite.db"; // this is the path to your SQLite DB file
        return DriverManager.getConnection(url);
    }
}

