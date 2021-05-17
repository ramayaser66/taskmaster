package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        Button taskA = MainActivity.this.findViewById(R.id.TaskAbtn);
        taskA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendTaskA = new Intent(MainActivity.this, TaskDetailPage.class);
                sendTaskA.putExtra("title", "taskA");
                startActivity(sendTaskA);



            }
        });


        Button taskB = MainActivity.this.findViewById(R.id.TaskBbtn);
        taskB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendTaskB = new Intent(MainActivity.this, TaskDetailPage.class);
                sendTaskB.putExtra("title", "taskB");
                startActivity(sendTaskB);



            }
        });

        Button taskC = MainActivity.this.findViewById(R.id.taskCbtn);
        taskC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendTaskC = new Intent(MainActivity.this, TaskDetailPage.class);
                sendTaskC.putExtra("title", "taskC");
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






    }
}