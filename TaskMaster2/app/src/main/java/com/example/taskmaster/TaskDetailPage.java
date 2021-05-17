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


        TextView taskAtitle = findViewById(R.id.detailpagetitle);
        Intent taskA = getIntent();

        String title = taskA.getStringExtra("title");
        taskAtitle.setText(title);

        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                " Phasellus fermentum nisi eget maximus posuere. Ut placerat finibus sapien non viverra." +
                " Donec a auctor mi. Nunc facilisis pretium vulputate. Aliquam erat volutpat. " +
                "Praesent elementum nisl lobortis nunc mollis commodo. Aliquam feugiat dui urna, vel ornare leo rhoncus quis. " +
                "Nam imperdiet mauris vitae condimentum sagittis. Ut justo erat, ornare sed nunc ac, laoreet porta turpis.";

        TextView taskAdescription = findViewById(R.id.detaildescription);
        taskAdescription.setText(description);



    }
}