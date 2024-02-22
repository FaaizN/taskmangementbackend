package com.taskmanagementapplication.taskmanagement.repository;

import com.taskmanagementapplication.taskmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM user_table u WHERE u.userName = :userName")
    boolean existsByUserName(String userName);

    List<User> findAll();

    User findByUserName(String userName);
}
