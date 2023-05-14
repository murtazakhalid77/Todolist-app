package com.example.projecmad;


import com.example.projecmad.Model.listsName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Api {

    String BASE_URL = "https://f68f-202-47-53-142.ngrok-free.app/";
    @GET("list/getAll")
    Call<List<listsName>> getAlllistNames();

}
