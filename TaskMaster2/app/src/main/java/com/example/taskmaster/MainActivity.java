package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button firstButton = MainActivity.this.findViewById(R.id.button);
        firstButton.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                Intent goToAddTask = new Intent (MainActivity.this, AddTask.class);
                goToAddTask.putExtra("key", "value");
                MainActivity.this.startActivity(goToAddTask);
            }
        });

        Button secondButton = MainActivity.this.findViewById(R.id.button2);
        secondButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent goToAllTask = new Intent (MainActivity.this, AllTasks.class);
                goToAllTask.putExtra("key", "value");
                MainActivity.this.startActivity(goToAllTask);
            }
        });



        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                " Phasellus fermentum nisi eget maximus posuere. Ut placerat finibus sapien non viverra." +
                " Donec a auctor mi. Nunc facilisis pretium vulputate. Aliquam erat volutpat. " +
                "Praesent elementum nisl lobortis nunc mollis commodo. Aliquam feugiat dui urna, vel ornare leo rhoncus quis. " +
                "Nam imperdiet mauris vitae condimentum sagittis. Ut justo erat, ornare sed nunc ac, laoreet porta turpis.";

        Button taskA = MainActivity.this.findViewById(R.id.TaskAbtn);
        taskA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendTaskA = new Intent(MainActivity.this, TaskDetailPage.class);
                sendTaskA.putExtra("title", "taskA");
                sendTaskA.putExtra("body", description);
                sendTaskA.putExtra("state", "complete");
                startActivity(sendTaskA);



            }
        });


        Button taskB = MainActivity.this.findViewById(R.id.TaskBbtn);
        taskB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendTaskB = new Intent(MainActivity.this, TaskDetailPage.class);
                sendTaskB.putExtra("title", "taskB");
                sendTaskB.putExtra("body", description);
                sendTaskB.putExtra("state", "new");
                startActivity(sendTaskB);



            }
        });

        Button taskC = MainActivity.this.findViewById(R.id.taskCbtn);
        taskC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendTaskC = new Intent(MainActivity.this, TaskDetailPage.class);
                sendTaskC.putExtra("title", "taskC");
                sendTaskC.putExtra("body", description);
                sendTaskC.putExtra("state", "Assigned");
                startActivity(sendTaskC);



            }
        });


        Button gotosettings = MainActivity.this.findViewById(R.id.settingsbtn);
        gotosettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingspage = new Intent(MainActivity.this, SettingsPage.class);
                MainActivity.this.startActivity(settingspage);
            }
        });

            TextView homeuser = findViewById(R.id.usernamehomepage);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String username = preferences.getString("username", "user");

        homeuser.setText(username +"'s page");





        ArrayList<Tasks> tasks = new ArrayList<>();
        tasks.add(new Tasks("Task one", description, "assigned"));
        tasks.add(new Tasks("Task two", description, "in progress"));
        tasks.add(new Tasks("Task three", description, "new"));
        tasks.add(new Tasks("Task four", description, "complete"));


        RecyclerView taskList = findViewById(R.id.recyclerView);
        taskList.setLayoutManager(new LinearLayoutManager(this));
        taskList.setAdapter(new TaskAdapter(   this, tasks));







    }
}