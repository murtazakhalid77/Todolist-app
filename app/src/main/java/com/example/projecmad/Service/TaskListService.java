package com.example.projecmad.Service;

import com.example.projecmad.Model.TaskList;
import com.example.projecmad.RetrofitConfigration.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskListService {

    public  boolean saveTaskInList(TaskList taskList) {
        final boolean[] backendresponse = new boolean[1];
        Call<TaskList> call = RetrofitClient.getInstance().getMyApi().saveTaskInList(taskList);

        call.enqueue(new Callback<TaskList>() {
            @Override
            public void onResponse(Call<TaskList> call, Response<TaskList> response) {
                if (response.isSuccessful()) {
                    backendresponse[0] = false;
                }
            }

            @Override
            public void onFailure(Call<TaskList> call, Throwable t) {
                backendresponse[0] = true;
            }
        });

        return backendresponse[0];
    }
}
