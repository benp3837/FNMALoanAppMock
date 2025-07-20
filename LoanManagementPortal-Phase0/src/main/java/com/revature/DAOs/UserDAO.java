package com.revature.DAOs;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements UserDAOInterface{

    @Override
    public User insertUser(User user) {

        //We will always try to connect to the DB first, before we can run any SQL
        try(Connection conn = ConnectionUtil.getConnection()){

            //create our SQL statement
            String sql = "INSERT INTO users (username, email, role, password) VALUES (?, ?, ?, ?)";

            //use PreparedStatement to fill in the values of our variables
            PreparedStatement ps = conn.prepareStatement(sql);

            //use the .set() methods from PreparedStatement to fill in the values
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getRole());
            ps.setString(4, user.getPassword());

            //Now that we've filled in the values, we can send the command to the DB
            ps.executeUpdate();
            //NOTE: executeUpdate() is used with INSERTS, UPDATES, and DELETES
            //...while executeQuery() is used with SELECTS

            System.out.println("User " + user.getUsername() + " inserted!");

            //We can then return the new User object (we can just use the method parameter)
            return user;

            //TODO: you could get the emp from the DB but it would be a bit more work
            //we could use some other getBy___ DAO method for users

        } catch (SQLException e){
            e.printStackTrace(); //tells us what went wrong
            System.out.println("Couldn't insert Employee");
        }

        //catch-all return statement - if something goes wrong, we'll return null
        return null;
    }


    @Override
    public User editUser(User user) {

        try (Connection conn = ConnectionUtil.getConnection()) {

            //create our SQL statement - just gonna do a full update
            //we could make separate methods or control flow for more specific updating
            String sql = "UPDATE users SET username = ?, email = ?, role = ?, password = ? WHERE user_id = ?";

            //use PreparedStatement to fill in the values of our variables
            PreparedStatement ps = conn.prepareStatement(sql);

            //use the .set() methods from PreparedStatement to fill in the values
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getRole());
            ps.setInt(4, user.getUserId());
            ps.setString(5, user.getPassword());

            //Now that we've filled in the values, we can send the command to the DB
            ps.executeUpdate();

            System.out.println("User " + user.getUsername() + " updated!");

            //We can then return the updated User object (we can just use the method parameter)
            return user;

        } catch (SQLException e) {
            e.printStackTrace(); //tell us what went wrong
        }

        return null;
    }



    //methods that aren't part of the requirements
    //(but we'll use them for validation and error handling in the service)----------------

    //This method gets a user by username (to make sure new users don't have the same username)
    public User getUserByUsername(String username){

        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM users WHERE username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            //Execute the query
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                //If we found a user, create a new User object and return it
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setPassword(rs.getString("password"));

                return user; //return the found user
            } else {
                //If no user was found, return null
                return null;
                //NOTE: we actually want null when inserting a new user
                //cuz that means the username isn't taken!
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Operation failed");
        }

        //catch all return statement
        return null;

    }

}
