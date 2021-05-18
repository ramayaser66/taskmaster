package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TaskDetailPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail_page);

        Intent intent = getIntent();

        String titlei = intent.getStringExtra("title");
        String bodyi = intent.getStringExtra("body");
        String statei = intent.getStringExtra("state");



      TextView state = findViewById(R.id.detailpagestae);
      state.setText(statei);

        TextView taskAtitle = findViewById(R.id.detailpagetitle);

        taskAtitle.setText(titlei);


        TextView taskAdescription = findViewById(R.id.detaildescription);
        taskAdescription.setText(bodyi);






    }
}