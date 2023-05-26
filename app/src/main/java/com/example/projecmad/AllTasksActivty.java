package com.example.projecmad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AllTasksActivty extends AppCompatActivity {

    public ListView mylist;
    public ArrayList<String> store;
    public ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks_activty);


        mylist = (ListView) findViewById(R.id.list_view);
        store = new ArrayList<>();
        store.add("Oily fish ");
        store.add("Luncheon Meat ");
        store.add("Pasta ");
        store.add("Rice ");
        store.add("Bread ");
        store.add("flour ");
        store.add("Cooking oil ");
        store.add("Butter ");
        store.add("Milk ");
        store.add("Cheese ");

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, store);
        mylist.setAdapter(arrayAdapter);


        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(AllTasksActivty.this, "Item: " + String.valueOf(position), Toast.LENGTH_LONG).show();

            }
        });
    }
}