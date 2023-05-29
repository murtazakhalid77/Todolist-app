package com.example.projecmad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.projecmad.Model.Task;
import com.example.projecmad.Service.ListService;
import com.example.projecmad.Service.TaskListService;
import com.example.projecmad.Service.TaskService;
import com.example.projecmad.Utils.MyArrayAdapter;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class AllTasksActivty extends AppCompatActivity {


    ImageView backbuttonImageView;
    public ListView listView;
    MyArrayAdapter adapter;
    public Spinner spinner;
    String taskDiscription;
    TextView emptyTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks_activty);
        spinner = (Spinner) findViewById(R.id.dropdown_menu_list);
        backbuttonImageView =(ImageView) findViewById(R.id.imageView2);
        emptyTextView = (TextView) findViewById(R.id.tv_empty_view);

        listView = (ListView) findViewById(R.id.list_view);
        Intent intent = getIntent();
        TaskListService taskListService = new TaskListService();

             adapter = new MyArrayAdapter(AllTasksActivty.this,intent.getStringExtra("selectedItem"), taskListService);

             listView.setAdapter(adapter);

             listView.setEmptyView(emptyTextView);


        if (spinner != null) {

            ListService listService = new ListService();
             adapter = new MyArrayAdapter(this, listService);
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
                    listView.setAdapter( adapter);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                System.out.println("nothing ");
            }
        });

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
////                Toast.makeText(AllTasksActivty.this, "Item: " + String.valueOf(position), Toast.LENGTH_LONG).show();
//                System.out.println(listView.getSelectedItem().toString());
//                TaskService taskService = new TaskService();
//                CompletableFuture<Task> taskFuture = taskService.getTaskByTaskDiscription(taskDiscription);
//
//                taskFuture.thenAccept(task -> {
//                    System.out.println("Received task: " + task.toString());
//                });
//
//                taskFuture.exceptionally(throwable -> {
//                    System.err.println("Error retrieving task: " + throwable.getMessage());
//                    return null;
//                });
//            }
//        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                TaskService taskService = new TaskService();
                CompletableFuture<Task> taskFuture =  taskService.getTaskByTaskDescription(selectedItem);

                taskFuture.thenAccept(task -> {
                    Intent intent = new Intent(AllTasksActivty.this, NewTask.class);

                    intent.putExtra("taskDescription", task.getDescription());
                    intent.putExtra("taskComment", task.getComment());
                    intent.putExtra("taskDueDate", task.getDueDate());
                    intent.putExtra("taskTime", task.getTime());
                    intent.putExtra("taskStatus", task.isStatus());

                    startActivity(intent);
                });

                taskFuture.exceptionally(throwable -> {
                    System.err.println("Error retrieving task: " + throwable.getMessage());
                    return null;
                });
            }
        });
        backbuttonImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainPage();


            }
        });

    }
    private void openMainPage() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }



}