package com.revature.DAOs;

import com.revature.models.User;

//We'll use this Interface to lay out the methods we want to implement for our User DAO
//We will implement this interface (write the actual code for the methods) in our UserDAO class

public interface UserDAOInterface {

    User insertUser(User user);

    User editUser(User user);



}
