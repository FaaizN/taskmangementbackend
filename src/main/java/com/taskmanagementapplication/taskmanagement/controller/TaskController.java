package com.taskmanagementapplication.taskmanagement.controller;

import com.taskmanagementapplication.taskmanagement.model.Task;
import com.taskmanagementapplication.taskmanagement.model.User;
import com.taskmanagementapplication.taskmanagement.service.TaskService;
import com.taskmanagementapplication.taskmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@CrossOrigin
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    // Add a task to the database
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody Task task, @RequestParam String userName){
        // Check to see username exists
        User user = userService.getUserByUserName(userName);
        if(user != null) {
            // Associate task with the retrieved user
            task.setUser(user);

            // Save the task
            taskService.saveTask(task);

            return ResponseEntity.ok("Task Created Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username");
        }
    }
}
