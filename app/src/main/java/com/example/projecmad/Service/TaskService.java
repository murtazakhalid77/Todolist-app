package com.example.projecmad.Service;

import android.util.Log;

import com.example.projecmad.Model.Task;
import com.example.projecmad.Model.listsName;
import com.example.projecmad.RetrofitConfigration.RetrofitClient;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskService {

    public static boolean saveTask(Task task) {
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
}
