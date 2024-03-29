package com.taskmanagementapplication.dataanalyzer.service;

import com.taskmanagementapplication.taskmanagement.model.Task;
import com.taskmanagementapplication.taskmanagement.model.User;
import com.taskmanagementapplication.taskmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class DataAnalyzerServiceImplementation implements DataAnalyzerService{

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public double analyzeTaskData(String userName){

        // Get the current month and year
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        // Retrieve user by userName
        User user = new User();
        user.setUserName(userName);

        // Count the completed tasks for the user within the current month
        List<Task> completedTasks = taskRepository.findUserCompleted(true);

        // Count the completed tasks for the user within the current month
        List<Task> totalTasks = taskRepository.findByUser(user);

        // Calculate the completion percentage
        double completionPercentage = 0.0;
        if(!totalTasks.isEmpty()) {
            completionPercentage = (double) completedTasks.size() / totalTasks.size() * 100.0;
        }
        return completionPercentage;
    }
}
