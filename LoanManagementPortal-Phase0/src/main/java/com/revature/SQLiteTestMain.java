package com.revature;

import com.revature.models.User;
import com.revature.utils.ConnectionUtilSQLite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteTestMain {

    public static void main(String[] args) {

        //selecting all records from the users table
        try(Connection conn = ConnectionUtilSQLite.getConnection()){

            //create our SQL statement
            String sql = "SELECT * FROM users";

            //use PreparedStatement to fill in the values of our variables
            PreparedStatement ps = conn.prepareStatement(sql);

            //Now that we've filled in the values, we can send the command to the DB
            ResultSet rs = ps.executeQuery();
            //NOTE: executeQuery() is used with SELECTS

            //Iterate through the ResultSet to get each User
            while(rs.next()) {
                //Create a new User object for each row in the ResultSet
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getString("password")
                );

                //Print out the User details
                System.out.println(user);
            }

            System.out.println("All users selected!");

        } catch (SQLException e){
            e.printStackTrace();
        }


        //Trying to insert User with SQLite
        //We will always try to connect to the DB first, before we can run any SQL
        try(Connection conn = ConnectionUtilSQLite.getConnection()){

            User u = new User("testuser", "test@email.com", "User", "password123");

            //create our SQL statement
            String sql = "INSERT INTO users (username, email, role, password) VALUES (?, ?, ?, ?)";

            //use PreparedStatement to fill in the values of our variables
            PreparedStatement ps = conn.prepareStatement(sql);

            //use the .set() methods from PreparedStatement to fill in the values
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getRole());
            ps.setString(4, u.getPassword());

            //Now that we've filled in the values, we can send the command to the DB
            ps.executeUpdate();
            //NOTE: executeUpdate() is used with INSERTS, UPDATES, and DELETES
            //...while executeQuery() is used with SELECTS

            System.out.println("User " + u.getUsername() + " inserted!");

            //TODO: you could get the emp from the DB but it would be a bit more work
            //we could use some other getBy___ DAO method for users

        } catch (SQLException e){
            e.printStackTrace(); //tells us what went wrong
            System.out.println("Couldn't insert Employee");
        }
    }

}
