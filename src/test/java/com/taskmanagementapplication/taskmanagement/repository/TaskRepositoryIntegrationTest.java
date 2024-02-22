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




//    @Test
//    public void testFindUserID() {
//        // Mocking data
//        int userId = 1; // Change this to match your actual user ID
//
//        User user = new User();
//        user.setUserID(userId); // Set the user ID using the setter method
//
//        // Save user to database
//        userRepository.save(user);
//
//
//        Task task1 = new Task();
//        task1.setTaskID(1);
//        task1.setUser(user); // Set the user for the task
//
//        Task task2 = new Task();
//        task2.setTaskID(2);
//        task2.setUser(user); // Set the user for the task
//
//
//        taskRepository.save(task1);
//        taskRepository.save(task2);
//
//        // Test the method
//        List<Task> tasks = taskRepository.findUserID(userId);
//        // assertThat(tasks).containsExactlyInAnyOrder(task1, task2);
//        assertThat(tasks)
//                .extracting(Task::getTaskID)
//                .containsExactlyInAnyOrderElementsOf(List.of(task1.getTaskID(), task2.getTaskID()));
//
//    }
//
//
//    @Test
//    public void testFindByUser() {
//        /*// Mocking data
//        User user = new User();
//        user.setUserID(1); // Set the user ID for the test user
//
//        // Save user to database
//        userRepository.save(user);
//
//        Task task1 = new Task();
//        task1.setTaskID(1);
//        task1.setUser(user); // Set the user for the task
//
//        Task task2 = new Task();
//        task2.setTaskID(2);
//        task2.setUser(user); // Set the user for the task
//
//
//        taskRepository.save(task1);
//        taskRepository.save(task2);
//
//        // Test the method
//        List<Task> tasks = taskRepository.findByUser(user);
//        assertThat(tasks).containsExactlyInAnyOrder(task1, task2);*/
//
//
//        // Retrieve an existing user from the database (assuming there is at least one user)
//        User user = userRepository.findAll().stream().findFirst()
//                .orElseThrow(() -> new RuntimeException("No user found in the database"));
//
//
//        // Create tasks and associate them with the retrieved user
//        Task task1 = new Task();
//        task1.setTaskID(1);
//        task1.setUser(user);
//
//        Task task2 = new Task();
//        task2.setTaskID(2);
//        task2.setUser(user);
//
//        // Save the tasks to the database
//        taskRepository.save(task1);
//        taskRepository.save(task2);
//
//        // Retrieve tasks associated with the same user
//        List<Task> tasks = taskRepository.findByUser(user);
//
//        // Verify that the retrieved tasks match the expected tasks
//        assertThat(tasks).containsExactlyInAnyOrder(task1, task2);
//
//
//    }

}
