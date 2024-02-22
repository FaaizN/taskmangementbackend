package com.taskmanagementapplication.datacollector.service;

import com.taskmanagementapplication.taskmanagement.model.Task;
import com.taskmanagementapplication.taskmanagement.model.User;

import java.util.List;

public interface DataCollectorService {
        List<Task> getAllTasks();
        List<User> getAllUsers();
        boolean doesUsernameExist(String userName);
        List<Task> getTasksByUserID(int userID);
}
