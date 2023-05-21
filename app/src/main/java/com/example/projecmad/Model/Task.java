package com.example.projecmad.Model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class Task {
    public Task(String description, String comment, boolean status, String dueDate, String time) {
        this.description = description;
        this.comment = comment;
        this.status = status;
        this.dueDate = dueDate;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @SerializedName("id")
    private Long id;

    @SerializedName("description")
    private String description;

    @SerializedName("comment")
    private String comment;

    @SerializedName("status")
    private boolean status;

    @SerializedName("dueDate")
    private String dueDate;

    @SerializedName("time")
    private String time;
}
