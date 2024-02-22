package com.taskmanagementapplication.taskmanagement.repository;

import com.taskmanagementapplication.taskmanagement.model.Task;
import com.taskmanagementapplication.taskmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("SELECT t FROM Task t WHERE t.completed = :completed")
    List<Task> findUserCompleted(@Param("completed") boolean completed);

    @Query("SELECT t FROM Task t WHERE t.user.user_id = :userID")
    List<Task> findUserID(@Param("userID") int userID);

    @Query("SELECT t FROM Task t WHERE t.user = :user")
    List<Task> findByUser(User user);

}
