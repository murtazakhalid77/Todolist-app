package com.example.projecmad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.projecmad.Model.listsName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.dropdown_menu);
        button = (Button) findViewById(R.id.newtask);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewTaskPage ();
            }
        });
//        getSuperHeroes();
    }
    public void openNewTaskPage(){
        Intent intent = new Intent(this, NewTask.class);
        startActivity(intent);
    }

    private void getSuperHeroes() {
        Call<List<listsName>> call = RetrofitClient.getInstance().getMyApi().getAlllistNames();
        call.enqueue(new Callback<List<listsName>>() {
            @Override
            public void onResponse(Call<List<listsName>> call, Response<List<listsName>> response) {
                List<listsName> myheroList = new ArrayList<>();
                myheroList.add(new listsName(1L,"select Anything"));
                 myheroList.addAll(response.body());
                String[] oneHeroes = new String[myheroList.size()];

                for (int i = 0; i < myheroList.size(); i++) {
                    oneHeroes[i] = myheroList.get(i).getName();
                }

                spinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, oneHeroes));
            }

            @Override
            public void onFailure(Call<List<listsName>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }
}
