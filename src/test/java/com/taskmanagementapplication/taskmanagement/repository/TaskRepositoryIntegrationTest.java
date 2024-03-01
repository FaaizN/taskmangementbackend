package com.taskmanagementapplication.taskmanagement.repository;


import com.taskmanagementapplication.taskmanagement.model.Task;
import com.taskmanagementapplication.taskmanagement.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TaskRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindUserCompleted() {

        // Mock the data
        User user = new User();
        user.setUserID(1);
        user.setUserName("testUser");

        Task completedTask = new Task();
        completedTask.setTaskID(1);
        completedTask.setUser(user);
        completedTask.setCompleted(true);

        Task incompleteTask = new Task();
        incompleteTask.setTaskID(2);
        incompleteTask.setUser(user);
        incompleteTask.setCompleted(false);

        // Save user to database
        userRepository.save(user);

        // Save the tasks directly to the database
        taskRepository.save(completedTask);
        taskRepository.save(incompleteTask);

        // Test method for completed tasks
        List<Task> completedTasks = taskRepository.findUserCompleted(true);
       // assertThat(completedTasks).containsExactly(completedTask);

       assertThat(completedTasks)
                .extracting(Task::getTaskID)
                .containsExactly(completedTask.getTaskID());


        // Test method for incomplete tasks
        List<Task> incompleteTasks = taskRepository.findUserCompleted(false);
        // assertThat(incompleteTasks).containsExactly(incompleteTask);
        assertThat(incompleteTasks)
                .extracting(Task::getTaskID)
                .containsExactly(incompleteTask.getTaskID());


    }

    @Test
    public void testFindUserID() {
        // Given
        User user = new User();
        user.setUserName("testUser");
        entityManager.persistAndFlush(user);

        Task task1 = new Task();
        task1.setUser(user);
        entityManager.persistAndFlush(task1);

        Task task2 = new Task();
        task2.setUser(user);
        entityManager.persistAndFlush(task2);

        // When
        List<Task> tasks = taskRepository.findUserID(user.getUserID());

        // Then
        assertThat(tasks).containsExactlyInAnyOrder(task1, task2);
    }

    @Test
    public void testFindByUser() {
        // Given
        User user = new User();
        user.setUserName("testUser");
        entityManager.persistAndFlush(user);

        Task task1 = new Task();
        task1.setUser(user);
        entityManager.persistAndFlush(task1);

        Task task2 = new Task();
        task2.setUser(user);
        entityManager.persistAndFlush(task2);

        // When
        List<Task> tasks = taskRepository.findByUser(user);

        // Then
        assertThat(tasks).containsExactlyInAnyOrder(task1, task2);
    }

}
