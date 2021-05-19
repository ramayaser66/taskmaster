package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TaskDetailPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail_page);

        // the back icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();

        String titlei = intent.getStringExtra("title");
        String bodyi = intent.getStringExtra("body");
        String statei = intent.getStringExtra("state");
        int idi = intent.getIntExtra("id",1);






      TextView state = findViewById(R.id.detailpagestae);
      state.setText(statei);

        TextView taskAtitle = findViewById(R.id.detailpagetitle);

        taskAtitle.setText(titlei);


        TextView taskAdescription = findViewById(R.id.detaildescription);
        taskAdescription.setText(bodyi);


        Button deleteBtn = findViewById(R.id.deletebtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase.getDatabase(getApplicationContext()).tasksDao().deleteById(idi);
                     // toast
                Toast.makeText(TaskDetailPage.this, "Task Deleted ", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(TaskDetailPage.this, MainActivity.class);
                startActivity(intent);

            }
        });








    }
}