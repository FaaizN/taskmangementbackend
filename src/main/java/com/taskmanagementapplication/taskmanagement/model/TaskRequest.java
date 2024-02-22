package com.taskmanagementapplication.taskmanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TaskRequest {
    private String taskName;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date dueDate;

    private boolean completed;
    private String userName; // Assuming this is the username associated with the task

    // Constructors, getters, and setters
    // Constructor
    public TaskRequest(String taskName, Date dueDate, boolean completed, String userName) {
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.completed = completed;
        this.userName = userName;
    }

    // Getters and Setters
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
