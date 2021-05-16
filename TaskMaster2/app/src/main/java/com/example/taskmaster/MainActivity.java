package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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





    }
}