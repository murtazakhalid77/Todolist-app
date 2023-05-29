package com.example.projecmad.RetrofitConfigration;


import com.example.projecmad.Model.TaskList;
import com.example.projecmad.Model.Task;
import com.example.projecmad.Model.listsName;

import java.util.List;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface Api {

    String BASE_URL = " https://02fb-202-47-60-102.ngrok-free.app/api/";
    @GET("list/getAll")
    Call<List<listsName>> getAlllistNames();

    @GET("todoList/{listName}")
    Call<List<TaskList>> getTaskByListName(@Path("listName") String listName);

    @POST("saveTask/task")
    Call<Task> saveTask(@Body Task task);

    @POST("taskSave/taskList")
    Call<TaskList>  saveTaskInList(@Body TaskList taskList);

    @GET("task/{description}")
    Call<Task> getTaskByTaskDescription(@Path("description") String description);

    @POST("list/saveList")
    Call<listsName> saveList(@Body listsName listsName);
}
