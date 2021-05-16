package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Button firstButton = AddTask.this.findViewById(R.id.button4);

        firstButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView myTask = findViewById(R.id.editTextTextPersonName);
                myTask.setVisibility(View.INVISIBLE);

                TextView taskdesc = findViewById(R.id.textView3);
                taskdesc.setText("Submitted");

                TextView dosmth= findViewById(R.id.editTextTextPersonName2);
                dosmth.setVisibility(View.INVISIBLE);

                TextView taskTile= findViewById(R.id.textView4);
                taskTile.setVisibility(View.INVISIBLE);

                TextView taskcount= findViewById(R.id.textView5);
                taskcount.setVisibility(View.INVISIBLE);

            }
        });

        
    }

}