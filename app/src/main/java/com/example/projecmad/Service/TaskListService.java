package com.example.projecmad.Service;

import com.example.projecmad.Model.Task;
import com.example.projecmad.Model.TaskList;
import com.example.projecmad.Model.listsName;
import com.example.projecmad.RetrofitConfigration.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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
    public CompletableFuture<String[]> getTaskByListName(String listName) {
        CompletableFuture<String[]> future = new CompletableFuture<>();

        Call<List<TaskList>> call = RetrofitClient.getInstance().getMyApi().getTaskByListName(listName);
        call.enqueue(new Callback<List<TaskList>>() {
            @Override
            public void onResponse(Call<List<TaskList>> call, Response<List<TaskList>> response) {
                List<TaskList> myheroList = response.body();

                if (myheroList != null) {
                    List<String> heroNames = new ArrayList<>();
                    for (TaskList hero : myheroList) {
                        heroNames.add(hero.getTask().getDescription());
                    }
                    future.complete(heroNames.toArray(new String[0]));
                } else {
                    future.complete(new String[0]); // or handle null response as desired
                }
            }

            @Override
            public void onFailure(Call<List<TaskList>> call, Throwable t) {
                future.completeExceptionally(t);
            }
        });

        return future;
    }
}
