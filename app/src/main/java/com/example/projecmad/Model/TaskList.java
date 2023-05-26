package com.example.projecmad.Model;

import com.google.gson.annotations.SerializedName;

public class TaskList {

    @SerializedName("id")
    private Long id;
    @SerializedName("list")
    private listsName list;
    @SerializedName("task")
    private   Task task;

    public TaskList(Long id, listsName list, Task task) {
        this.id = id;
        this.list = list;
        this.task = task;
    }

    public TaskList(listsName list, Task task) {
        this.list = list;
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public listsName getList() {
        return list;
    }

    public void setList(listsName list) {
        this.list = list;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}

