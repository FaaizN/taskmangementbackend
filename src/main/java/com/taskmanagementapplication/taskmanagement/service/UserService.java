package com.taskmanagementapplication.taskmanagement.service;

import com.taskmanagementapplication.taskmanagement.model.User;

public interface UserService {
    boolean existsByUserName(String userName);
    public User saveUser(User user);
    public User getUserByID(int userID);
    User getUserByUserName(String userName);

}
