package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String fileName = intent.getStringExtra("fileName");






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


        ImageView uploadedImage = findViewById(R.id.imageView);
        TextView attachmentTitle = findViewById(R.id.attatchedfileTitle);
        TextView attachmentLink = findViewById(R.id.attatchedFilecontent);





//        Amplify.Storage.downloadFile(
//                "thisIsMyKey",
//                new File(getApplicationContext().getFilesDir() + "/testImage.jpg"),
//                result -> Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile().getName()),
//                // change .getname ---> touri or topath to get it as a link
//                error -> Log.e("MyAmplifyApp",  "Download Failure", error)
//        );


        if(fileName != null){
            File outFile=new File(getApplicationContext().getFilesDir() + fileName) ;
            Amplify.Storage.downloadFile(
                    fileName,
                    outFile,
                    result -> {Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile().getTotalSpace());

                        String uploadedAttachment = attachmentType(result.getFile().getPath());











                        Pattern pattern = Pattern.compile("^image/",Pattern.CASE_INSENSITIVE);
                        Matcher matcher = pattern.matcher(uploadedAttachment);
                        boolean isTheAttachmentAnImage =matcher.find();



                        if(isTheAttachmentAnImage){
                                String filePath = result.getFile().getPath();
                            Bitmap bitmap = BitmapFactory.decodeFile(filePath);

                            uploadedImage.setImageBitmap(bitmap);
                            uploadedImage.setVisibility(View.VISIBLE);
                        }
                        else{
                            String FileURL = "https://taskmasterbucket113600-dev.s3.amazonaws.com/public/"+fileName;
                            attachmentLink.setText(FileURL);
                            attachmentLink.setVisibility(View.VISIBLE);
                            attachmentTitle.setVisibility(View.VISIBLE);

                        }


                    },
                    error -> Log.e("MyAmplifyApp",  "Download Failure", error)
            );
        }






    }


    public  String attachmentType(String filePath) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(filePath);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }




}