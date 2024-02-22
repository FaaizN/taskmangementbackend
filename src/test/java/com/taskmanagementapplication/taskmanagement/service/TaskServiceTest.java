package com.taskmanagementapplication.taskmanagement.service;
import com.taskmanagementapplication.taskmanagement.model.Task;
import com.taskmanagementapplication.taskmanagement.model.User;
import com.taskmanagementapplication.taskmanagement.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImplementation taskService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveTask() {

        Task task = new Task();

        // Mock behavior of taskRepository.save(task) to return the same task object
        when(taskRepository.save(task)).thenReturn(task);

        // Call saveTask method of taskService with task object
        Task savedTask = taskService.saveTask(task);

        // Verify returned object and passed object to saveTask are the same
        assertEquals(task, savedTask);

        // Verify the save method was called once
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testGetTasksByUser() {
        User user = new User();
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        tasks.add(new Task());

        // Mock the behavior of taskRepository.findByUser(user) to return the list of tasks
        when(taskRepository.findByUser(user)).thenReturn(tasks);

        // Call the getTasksByUser method of taskService with the user object
        List<Task> retrievedTasks = taskService.getTasksByUser(user);

        // Verify that the size of the retrievedTasks list is equal to the size of the tasks list
        assertEquals(tasks.size(), retrievedTasks.size());

        // Verify that each task in the retrievedTasks list is equal to the corresponding task in the tasks list
        assertEquals(tasks.get(0), retrievedTasks.get(0));
        assertEquals(tasks.get(1), retrievedTasks.get(1));

        // Verify that the findByUser method of taskRepository was called exactly once with the user object
        verify(taskRepository, times(1)).findByUser(user);
    }
}
