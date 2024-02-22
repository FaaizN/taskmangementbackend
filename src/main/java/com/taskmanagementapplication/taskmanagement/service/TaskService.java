package com.taskmanagementapplication.taskmanagement.service;

import com.taskmanagementapplication.taskmanagement.model.Task;
import com.taskmanagementapplication.taskmanagement.model.User;

import java.util.List;

public interface TaskService {

    public Task saveTask(Task task);
    List<Task> getTasksByUser(User user);
}
