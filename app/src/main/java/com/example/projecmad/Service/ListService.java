package com.example.projecmad.Service;

import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.projecmad.MainActivity;
import com.example.projecmad.Model.Task;
import com.example.projecmad.Model.listsName;
import com.example.projecmad.RetrofitConfigration.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListService {
    public CompletableFuture<String[]> getListNames() {
        CompletableFuture<String[]> future = new CompletableFuture<>();

        Call<List<listsName>> call = RetrofitClient.getInstance().getMyApi().getAlllistNames();
        call.enqueue(new Callback<List<listsName>>() {
            @Override
            public void onResponse(Call<List<listsName>> call, Response<List<listsName>> response) {
                List<listsName> myheroList = response.body();

                if (myheroList != null) {
                    List<String> heroNames = new ArrayList<>();
                    heroNames.add("Select List");
                    for (listsName hero : myheroList) {
                        heroNames.add(hero.getName());
                    }
                    future.complete(heroNames.toArray(new String[0]));
                } else {
                    future.complete(new String[0]); // or handle null response as desired
                }
            }

            @Override
            public void onFailure(Call<List<listsName>> call, Throwable t) {
                future.completeExceptionally(t);
            }
        });

        return future;
    }

    public  boolean saveList(listsName listsName) {
        final boolean[] backendresponse = new boolean[1];
        Call<listsName> call = RetrofitClient.getInstance().getMyApi().saveList(listsName);

        call.enqueue(new Callback<listsName>() {
            @Override
            public void onResponse(Call<listsName> call, Response<listsName> response) {
                if (response.isSuccessful()) {
                    backendresponse[0] = false;
                }
            }

            @Override
            public void onFailure(Call<listsName> call, Throwable t) {
                backendresponse[0] = true;
            }
        });

        return backendresponse[0];
    }


}
