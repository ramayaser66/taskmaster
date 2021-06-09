package com.example.taskmaster;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

//
//import static com.example.taskmaster.AppDatabase.databaseWriteExecutor;

public class AddTask extends AppCompatActivity {
EditText title ,body,state;

    String fileName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        // the back icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button firstButton = AddTask.this.findViewById(R.id.button4);
//        TextView taskAdded = findViewById(R.id.taskadded);
//        taskAdded.setVisibility(View.GONE);



        title = findViewById(R.id.editTextTextPersonName);
        body = findViewById(R.id.editTextTextPersonName2);
        state = findViewById(R.id.editTextStateInAddTask);

        // upload btn
        Button uoload = findViewById(R.id.uploadbtn);

        List<Tasks> tasks = AppDatabase.getDatabase(getApplicationContext()).tasksDao().getAll();
        TextView taskcount= findViewById(R.id.textView5);
        taskcount.setText("Total Tasks: "+tasks.size());




                firstButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){


                String stringTitle = title.getText().toString();
                String stringBody = body.getText().toString();
                String stringState = state.getText().toString();



                if(!stringTitle.isEmpty() && !stringBody.isEmpty() && !stringState.isEmpty()){

                    Tasks tasksi = new Tasks();
                    tasksi.setTitle(stringTitle);
                    tasksi.setBody(stringBody);
                    tasksi.setState(stringState);
                    tasksi.setFile(fileName);



                    Toast.makeText(AddTask.this, fileName+"*", Toast.LENGTH_SHORT).show();


                    // create a Toast
//                    Toast.makeText(AddTask.this, "Task Added! ", Toast.LENGTH_SHORT).show();

//                        AppDatabase.getDatabase(getApplicationContext()).tasksDao().insertAll(tasksi);
                    // replace this with the amplify code
                    AppDatabase.getDatabase(getApplicationContext()).tasksDao().insertTasks(tasksi);

//                    Task item = Task.builder()
//                            .title(stringTitle)
//                            .description(stringBody)
//                            .status(stringState)
//                            .build();
//
//
//                    Amplify.DataStore.save(item,
//                            success -> Log.i("Tutorial", "Saved item: " + success.item().getTitle()),
//                            error -> Log.e("Tutorial", "Could not save item to DataStore", error)
//                    );



//
//                    Intent intent = new Intent(AddTask.this, MainActivity.class);
//                    startActivity(intent);

                    finish();

                }else{
                    Toast.makeText(AddTask.this, "Please Add Data", Toast.LENGTH_SHORT).show();
                }





//                TextView myTask = findViewById(R.id.editTextTextPersonName);
//                myTask.setVisibility(View.INVISIBLE);
//
//                TextView taskdesc = findViewById(R.id.textView3);
////                taskdesc.setText("Submitted");
//
//                TextView dosmth= findViewById(R.id.editTextTextPersonName2);
////                dosmth.setVisibility(View.INVISIBLE);
//
//                TextView taskTile= findViewById(R.id.textView4);
////                taskTile.setVisibility(View.INVISIBLE);
//
//                TextView taskcount= findViewById(R.id.textView5);
////                taskcount.setVisibility(View.INVISIBLE);
//
//
//                taskAdded.setVisibility(View.VISIBLE);
//                taskAdded.setText("Task added!");




//                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
//                        AppDatabase.class, "tasksdb").build();

//                TasksDao tasksDao = db.tasksDao();


//                databaseWriteExecutor.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        Tasks tasksi = new Tasks();
//                        tasksi.setTitle(stringTitle);
//                        tasksi.setBody(stringBody);
//                        tasksi.setState(stringState);


                        // create a Toast
//                Toast.makeText(AddTask.this, "Task Added! ", Toast.LENGTH_SHORT).show();
//
//
////                        AppDatabase.getDatabase(getApplicationContext()).tasksDao().insertAll(tasksi);
//                        AppDatabase.getDatabase(getApplicationContext()).tasksDao().insertTasks(tasksi);
//                        Intent intent = new Intent(AddTask.this, MainActivity.class);
//                        startActivity(intent);

//                    }
//                });

            }
        });

                uoload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("*/*");
                        startActivityForResult(intent,9999);

                    }
                });

        
    }





    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // how to upload the files of a specific type --> use uri insted of copying the file yourself
        if(requestCode ==9999){

            Uri getUri = data.getData();
            Cursor returnCursor = getContentResolver().query(getUri, null, null, null, null);
            int namei = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            returnCursor.moveToFirst();


            String UplodedFileName = returnCursor.getString(namei );
            this.fileName = UplodedFileName;



            File file = new File(getApplicationContext().getFilesDir(), UplodedFileName);
            try{
                InputStream inputStream = getContentResolver().openInputStream(getUri);

                  FileUtils.copy(inputStream, new FileOutputStream(file));
                  //the key will be the name of the file that you willl be getting form the intent
//                  uploadfile(file,UplodedFileName);

                Amplify.Storage.uploadFile(
                        UplodedFileName,
                        file,
                        result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                        storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
                );



            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




}