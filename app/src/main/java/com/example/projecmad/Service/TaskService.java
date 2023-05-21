package com.example.projecmad.Service;

import com.example.projecmad.Model.Task;
import com.example.projecmad.RetrofitConfigration.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskService {

    public static Boolean[] saveTask(Task task) {
        final Boolean[] responsebacked = {false};
        Call<Task> call = RetrofitClient.getInstance().getMyApi().saveTask(task);

        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {

                if (response.isSuccessful()) {
                    responsebacked[0] =true;
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                responsebacked[0] =false;
            }});

        return responsebacked;
    }
}
