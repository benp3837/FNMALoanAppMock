package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtilSQLite {

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("problem occurred locating SQLite driver");
        }

        String url = "jdbc:sqlite:Phase0WithSQLite.db"; // this is the path to your SQLite DB file

        Connection conn = DriverManager.getConnection(url); //create the connection!

        //Enable foreign key constraints
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON"); //look familiar?
        } catch (SQLException e) {
            System.out.println("Failed to enable foreign keys!");
            e.printStackTrace();
        }

        return conn; //return the connection. This is what our DAO methods will use.
    }
}

