package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class AllTasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);
        RecyclerView taskList = findViewById(R.id.recyclerView);

        // the back icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        List<Tasks> tasks = AppDatabase.getDatabase(getApplicationContext()).tasksDao().getAll();
        taskList.setLayoutManager(new LinearLayoutManager(AllTasks.this));
        taskList.setAdapter(new TaskAdapter(AllTasks.this,tasks));
    }
}