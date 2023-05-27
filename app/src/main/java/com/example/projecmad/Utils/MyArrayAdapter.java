package com.example.projecmad.Utils;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.projecmad.Service.ListService;
import com.example.projecmad.Service.TaskListService;

public class MyArrayAdapter extends ArrayAdapter<String> {
    private Context context;

    public MyArrayAdapter(Context context,ListService listService) {
        super(context, android.R.layout.simple_list_item_1);
        this.context = context;
        fetchListNames(listService);
    }
    public MyArrayAdapter(Context context,String listName,TaskListService taskListService) {
        super(context, android.R.layout.simple_list_item_1);
        this.context = context;
        fecthTaskNames(taskListService,listName);
    }
    private void fecthTaskNames(TaskListService taskListService,String listName){
        taskListService.getTaskByListName(listName).thenAccept(oneHeroes -> {
            clear();
            addAll(oneHeroes);
            notifyDataSetChanged();
        }).exceptionally(e -> {
            Toast.makeText(context, "Failed to fetch task names", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return null;
        });
    }

    private void fetchListNames(ListService listService) {

        listService.getListNames().thenAccept(oneHeroes -> {
            clear();
            addAll(oneHeroes);
            notifyDataSetChanged();
        }).exceptionally(e -> {
            Toast.makeText(context, "Failed to fetch list names", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return null;
        });
    }
}
