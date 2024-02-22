package com.taskmanagementapplication.datacollector.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.taskmanagementapplication.taskmanagement.model.Task;
import com.taskmanagementapplication.taskmanagement.model.User;
import com.taskmanagementapplication.taskmanagement.repository.TaskRepository;
import com.taskmanagementapplication.taskmanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataCollectorServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DataCollectorServiceImplementation dataCollectorService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoesUsernameExist() {
        when(userRepository.existsByUserName("existingUser")).thenReturn(true);
        when(userRepository.existsByUserName("nonExistingUser")).thenReturn(false);

        assertTrue(dataCollectorService.doesUsernameExist("existingUser"));
        assertFalse(dataCollectorService.doesUsernameExist("nonExistingUser"));
    }

    @Test
    public void testGetAllTasks() {
        List<Task> tasks = new ArrayList<>();

        Task task1 = new Task();
        task1.setTaskName("Task 1");
        tasks.add(task1);

        Task task2 = new Task();
        task2.setTaskName(null);
        tasks.add(task2);

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = dataCollectorService.getAllTasks();

        assertEquals(2, result.size());
        assertEquals("Task 1", result.get(0).getTaskName());
        assertEquals("Default", result.get(1).getTaskName());
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setUserName("User 1");
        users.add(user1);

        User user2 = new User();
        user2.setUserName(null);
        users.add(user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = dataCollectorService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("User 1", result.get(0).getUserName());
        assertEquals("NA", result.get(1).getUserName());
    }

    @Test
    public void testGetTaskByUserID() {
        User user = new User();
        user.setUserID(123);
        List<Task> tasks = new ArrayList<>();

        Task task1 = new Task();
        task1.setTaskID(1);
        task1.setUser(user);
        tasks.add(task1);

        Task task2 = new Task();
        task2.setTaskID(2);
        task2.setUser(user);
        tasks.add(task2);

        when(taskRepository.findUserID(user.getUserID())).thenReturn(tasks);

        List<Task> result = dataCollectorService.getTasksByUserID(user.getUserID());

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getTaskID());
        assertEquals(2, result.get(1).getTaskID());
        assertEquals(user.getUserID(), result.get(0).getUser().getUserID());
        assertEquals(user.getUserID(), result.get(0).getUser().getUserID());

    }

}
