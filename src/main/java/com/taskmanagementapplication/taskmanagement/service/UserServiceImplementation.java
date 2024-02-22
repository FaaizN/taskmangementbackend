package com.taskmanagementapplication.taskmanagement.service;

import com.taskmanagementapplication.taskmanagement.model.User;
import com.taskmanagementapplication.taskmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Override
    public boolean existsByUserName(String userName){
        return userRepository.existsByUserName(userName);
    }

    @Override
    public User getUserByID(int userID) {
        return userRepository.findById(userID).orElse(null);
    }

    @Override
    public User getUserByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
