package com.revature.DAOs;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAO {

    //Skipping the Interface because I'm very lazy

    //:)

    public User login(User user) {

        //Get a connection to the database
        try(Connection conn = ConnectionUtil.getConnection()){

            //Write the SQL query to check if the user exists
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

            //Prepare the statement
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());

            //Execute the query
            ResultSet rs = ps.executeQuery();

            //Check if we got a result
            if(rs.next()) {
                //If we found a user, create a new User object and return it
                User loggedInUser = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getString("password")
                );

                return loggedInUser; //return the found user
            } else {
                //If no user was found, return null
                System.out.println("No user found with the provided credentials.");
                return null; //Login failed
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null; //We can write the return here instead of below
        }

    }

}
