package com.example.projecmad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.projecmad.Model.TaskList;
import com.example.projecmad.Service.ListService;
import com.example.projecmad.Service.TaskListService;
import com.example.projecmad.Utils.MyArrayAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class AllTasksActivty extends AppCompatActivity {


    public ListView listView;
    public Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks_activty);
        spinner = (Spinner) findViewById(R.id.dropdown_menu_list);

        listView = (ListView) findViewById(R.id.list_view);

        if (spinner != null) {

            ListService listService = new ListService();
            MyArrayAdapter adapter = new MyArrayAdapter(this, listService);
            spinner.setAdapter(adapter);
        }

//        if (listView != null) {
//            TaskListService taskListService = new TaskListService();
//           // CompletableFuture<String[]> taskFuture = taskListService.getTaskByListName("Evening");
//            if (!spinner.getSelectedItem().equals("Select List")){
//                MyArrayAdapter adapter = new MyArrayAdapter(this,spinner.getSelectedItem().toString(), taskListService);
//                listView.setAdapter(adapter);
//            };
//        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TaskListService taskListService = new TaskListService();
                if (!spinner.getSelectedItem().equals("Select List")) {
                    MyArrayAdapter adapter = new MyArrayAdapter(AllTasksActivty.this, parent.getItemAtPosition(position).toString(), taskListService);
                    listView.setAdapter(adapter);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                System.out.println("nothing ");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(AllTasksActivty.this, "Item: " + String.valueOf(position), Toast.LENGTH_LONG).show();

            }
        });
    }


}