package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;
import java.util.List;

public class AllTasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);


//        try {
//            Amplify.addPlugin(new AWSDataStorePlugin());
//            Amplify.configure(getApplicationContext());
//
//            Log.i("Tutorial", "Initialized Amplify");
//        } catch (AmplifyException e) {
//            Log.e("Tutorial", "Could not initialize Amplify", e);
//        }




        RecyclerView taskList = findViewById(R.id.recyclerView);

        // the back icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        // replace with amplify

        // room
        List<Tasks> tasks = AppDatabase.getDatabase(getApplicationContext()).tasksDao().getAll();
        taskList.setLayoutManager(new LinearLayoutManager(AllTasks.this));
        taskList.setAdapter(new TaskAdapter(AllTasks.this,tasks));




        taskList.setLayoutManager(new LinearLayoutManager(AllTasks.this));
//        Amplify.DataStore.query(Task.class,
//                task -> {
//                    List<Tasks> tasks = new ArrayList<>();
//                    while (task.hasNext()) {
//                        Task todo = task.next();
//                        Tasks newTask = new Tasks();
//
//
//                        newTask.setTitle(todo.getTitle());
//                        newTask.setBody(todo.getDescription());
//                        newTask.setState(todo.getStatus());
//
//
//                        tasks.add(newTask);
//
//                        taskList.setAdapter(new TaskAdapter(AllTasks.this,tasks));
//
//
//                        Log.i("Tutorial", "==== Todo ====");
//                        Log.i("Tutorial", "Name: " + todo.getTitle());
//                    }
//                },
//                failure -> Log.e("Tutorial", "Could not query DataStore", failure)
//        );
//
    }
}