package com.taskmanagementapplication.dataanalyzer.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.taskmanagementapplication.datacollector.service.DataCollectorServiceImplementation;
import com.taskmanagementapplication.taskmanagement.model.Task;
import com.taskmanagementapplication.taskmanagement.model.User;
import com.taskmanagementapplication.taskmanagement.repository.TaskRepository;
import jakarta.inject.Inject;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class DataAnalyzerServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private DataAnalyzerServiceImplementation dataAnalyzerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAnalyzeTaskData() {
        String userName = "john doe";

        List<Task> completedTasks = new ArrayList<>();

        completedTasks.add(new Task());
        completedTasks.add(new Task());

        List<Task> totalTasks = new ArrayList<>();
        totalTasks.add(new Task());
        totalTasks.add(new Task());
        totalTasks.add(new Task());

        when(taskRepository.findUserCompleted(true)).thenReturn(completedTasks);
        when(taskRepository.findByUser(any())).thenReturn(totalTasks);

        double completionPercentage = dataAnalyzerService.analyzeTaskData(userName);

        assertEquals(66.67, completionPercentage, 0.01);
    }

    @Test
    public void testAnalyzeTaskData_ZeroTotalTasks() {
        String userName = "john doe";
        List<Task> completedTasks = new ArrayList<>();
        completedTasks.add(new Task());

        List<Task> totalTasks = new ArrayList<>();

        when(taskRepository.findUserCompleted(true)).thenReturn(completedTasks);
        when(taskRepository.findByUser(any())).thenReturn(totalTasks);

        double completionPercentage = dataAnalyzerService.analyzeTaskData(userName);

        assertEquals(0.0, completionPercentage);
    }

    @Test
    public void testAnalyzeTaskData_ZeroCompletionPercentage() {
        String userName = "John Doe";
        List<Task> completedTasks = new ArrayList<>();

        List<Task> totalTasks = new ArrayList<>();
        totalTasks.add(new Task());
        totalTasks.add(new Task());
        totalTasks.add(new Task());

        when(taskRepository.findUserCompleted(true)).thenReturn(completedTasks);
        when(taskRepository.findByUser(any())).thenReturn(totalTasks);

        double completionPercentage = dataAnalyzerService.analyzeTaskData(userName);

        assertEquals(0.0, completionPercentage);
    }
}
