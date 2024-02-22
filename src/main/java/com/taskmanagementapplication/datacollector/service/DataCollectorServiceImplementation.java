package com.taskmanagementapplication.datacollector.service;

import com.taskmanagementapplication.taskmanagement.model.Task;
import com.taskmanagementapplication.taskmanagement.model.User;
import com.taskmanagementapplication.taskmanagement.repository.TaskRepository;
import com.taskmanagementapplication.taskmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

@Service
public class DataCollectorServiceImplementation implements  DataCollectorService{

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean doesUsernameExist(String userName) {
        return userRepository.existsByUserName(userName);
    }


    @Override
    public List<Task> getAllTasks() {
        List<Task> allTasks = taskRepository.findAll();
        allTasks.forEach(task -> {
            // Filter the names for null values
            if (task.getTaskName() == null) {
                // Set the default value
                task.setTaskName("Default");
            }

            // Filter the Date for null values
            if (task.getDueDate() == null) {
                // Create Date
                Date currentDate = Calendar.getInstance().getTime();

                // Format the date to "YYYY-MM-DD" format
                SimpleDateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");
                String formattedDate = dateFormat.format(currentDate);
                try {
                    Date date = dateFormat.parse(formattedDate);
                    task.setDueDate(date);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return allTasks;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        allUsers.forEach(user ->{
            // Filter the name for null values
            if (user.getUserName() == null){
                // Set the default value
                user.setUserName("NA");
            }
        });
        return allUsers;
    }

    public List<Task> getTasksByUserID(int  userID){
       return taskRepository.findUserID(userID);
    }

}
