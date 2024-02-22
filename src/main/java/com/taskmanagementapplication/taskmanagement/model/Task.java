package com.taskmanagementapplication.taskmanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Task {

    // Set ID as primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int task_id;

    @Column(name = "task_name")
    @JsonProperty("task_name")
    private String taskName;

    @Column(name = "due_date")
    @JsonProperty("due_date")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date dueDate;

    private boolean completed;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Task() {
    }

    public int getTaskID() {
        return task_id;
    }

    public void setTaskID(int taskID) {
        this.task_id = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}
}
