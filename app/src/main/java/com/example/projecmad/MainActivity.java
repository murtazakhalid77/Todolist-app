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
import com.example.projecmad.RetrofitConfigration.RetrofitClient;
import com.example.projecmad.Service.ListService;
import com.example.projecmad.Utils.MyArrayAdapter;

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

        spinner = (Spinner) findViewById(R.id.dropdown_menu_list);
        button = (Button) findViewById(R.id.newtask);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewTaskPage ();
            }
        });

        if (spinner != null) {
//             Create the ArrayAdapter with the appropriate context
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,new ListService().getListNames());
            ListService listService = new ListService();
            MyArrayAdapter adapter = new MyArrayAdapter(this,listService);
//             Set the adapter for the spinner
            spinner.setAdapter(adapter);
        }
    }
    public void openNewTaskPage(){
        Intent intent = new Intent(this, NewTask.class);
        startActivity(intent);
    }



}

