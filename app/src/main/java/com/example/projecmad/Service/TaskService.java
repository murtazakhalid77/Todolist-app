package com.example.projecmad.Service;

import com.example.projecmad.Model.Task;
import com.example.projecmad.RetrofitConfigration.RetrofitClient;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskService {

    public  boolean saveTask(Task task) {
        final boolean[] backendresponse = new boolean[1];
        Call<Task> call = RetrofitClient.getInstance().getMyApi().saveTask(task);

        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if (response.isSuccessful()) {
                    backendresponse[0] = false;
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                backendresponse[0] = true;
            }
        });

        return backendresponse[0];
    }

    public CompletableFuture<Task> getTaskByTaskDescription(String taskDescription) {
        CompletableFuture<Task>future = new CompletableFuture<>();

        Call<Task> call = RetrofitClient.getInstance().getMyApi().getTaskByTaskDescription(taskDescription);
        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if (response.isSuccessful()) {
                    Task task = response.body();
                    future.complete(task);
                } else {
                    future.completeExceptionally(new Exception("Failed to retrieve task."));
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                future.completeExceptionally(t);
            }
        });

        return future;
    }

}
