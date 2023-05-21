package com.example.projecmad.RetrofitConfigration;


import com.example.projecmad.Model.Task;
import com.example.projecmad.Model.listsName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface Api {

    String BASE_URL = " https://e732-202-47-53-142.ngrok-free.app/api/";
    @GET("list/getAll")
    Call<List<listsName>> getAlllistNames();

    @POST("saveTask/task")
    Call<Task> saveTask(@Body Task task);

}
