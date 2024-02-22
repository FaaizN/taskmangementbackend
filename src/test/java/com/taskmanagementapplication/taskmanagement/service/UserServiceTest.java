package com.taskmanagementapplication.taskmanagement.service;
import com.taskmanagementapplication.taskmanagement.model.User;
import com.taskmanagementapplication.taskmanagement.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImplementation userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        User user = new User();

        // Mock the behavior of the userRepository.save(user) to return the same user object
        when(userRepository.save(user)).thenReturn(user);

        // Call the saveUser method of userService with the user Object
        User savedUser = userService.saveUser(user);

        // Verify that returned service savedUser is same as user object passed
        assertEquals(user, savedUser);

        // Verify save method was called once
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testExistsByUserName() {
        String userName = "testUser";

        // Mock the behavior of userRepository.existsByUserName(userName) to return true
        when(userRepository.existsByUserName(userName)).thenReturn(true);

        // Call ExistsByUserName
        boolean exists = userService.existsByUserName(userName);

        // Verify that existsByUserName returns true
        assertTrue(exists);

        // Verify that existsByUserName  method was called once
        verify(userRepository, times(1)).existsByUserName(userName);
    }

    @Test
    public void testGetUserByID() {
        int userID = 1;

        User user = new User();
        user.setUserID(userID);

        // Mock behavior of userRepository.findByID to return the user object
        when(userRepository.findById(userID)).thenReturn(java.util.Optional.of(user));

        // Call the getUserByID method of userService
        User retreivedUser = userService.getUserByID(userID);

        // Verify that retrievedUser is the same as the user object with specified ID
        assertEquals(user, retreivedUser);

        // Verify that the findByID method was called once
        verify(userRepository, times(1)).findById(userID);
    }


    @Test
    public void testGetUserByUserName() {
        String userName = "testUser";

        User user = new User();

        // Mock the behavior of userRepository.findByUserName(userName)
        when(userRepository.findByUserName(userName)).thenReturn(user);

        // Call the getUserByUserName method
        User retrievedUser = userService.getUserByUserName(userName);

        // Verify the retrievedUser is the same as the user object
        assertEquals(user, retrievedUser);

        // Verify that findByUserName is run once
        verify(userRepository, times(1)).findByUserName(userName);
    }
}
