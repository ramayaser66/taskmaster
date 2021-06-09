package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//import static com.example.taskmaster.AppDatabase.databaseWriteExecutor;

public class MainActivity extends AppCompatActivity {

    Button signup, login, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// for cognito - amplify
        try {
            // Add this line, to include the Auth plugin.
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }




////
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<String> task) {

                        if (!task.isSuccessful()) {
                            Log.w("TokenMessage", "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        String token = task.getResult();
                        // Log and toast
                        Log.d("notification recieved", " notification recieved ");
                        Log.d("TokenGenerated", token);
//                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });

       //



        TextView homeuser = findViewById(R.id.usernamehomepage);
      signup = findViewById(R.id.signup);
       login = findViewById(R.id.signin);
        logout = findViewById(R.id.logout);

       signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent goToSignUp = new Intent(MainActivity.this, SignUp.class);
               startActivity(goToSignUp);
           }
       });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLogin = new Intent(MainActivity.this, Login.class);
                startActivity(goToLogin);
            }
        });




        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.signOut(
                        AuthSignOutOptions.builder().globalSignOut(true).build(),
                        () -> Log.i("AuthQuickstart", "Signed out globally"),
                        error -> Log.e("AuthQuickstart", error.toString())
                );
            }
        });

        Intent II = getIntent();
        String userName = II.getStringExtra("userName");

        if(userName != null){
            homeuser.setText(userName +"'s page");

        }

        // add a clivk listener for uploading
//        getFileFromMobileStorage();


        // you will need tp rovied the option to view or download the file
        // chaneged the file type from txt ot jpg for now

//        Amplify.Storage.downloadFile(
//                "thisIsMyKey",
//                new File(getApplicationContext().getFilesDir() + "/testImage.jpg"),
//                result -> Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile().getName()),
//                // change .getname ---> touri or topath to get it as a link
//                error -> Log.e("MyAmplifyApp",  "Download Failure", error)
//        );

        // add an imape view on each single task so if the attatchment is an image it will be dispalied in the take, and provied a download link for the other types of filesto get it to your file




        
        // for the room lab
//        try {
//            Amplify.addPlugin(new AWSDataStorePlugin());
//            Amplify.configure(getApplicationContext());
//
//            Log.i("Tutorial", "Initialized Amplify");
//        } catch (AmplifyException e) {
//            Log.e("Tutorial", "Could not initialize Amplify", e);
//        }

        // go to add Task page
        Button firstButton = MainActivity.this.findViewById(R.id.button);
        firstButton.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                Intent goToAddTask = new Intent (MainActivity.this, AddTask.class);
                goToAddTask.putExtra("key", "value");
                MainActivity.this.startActivity(goToAddTask);
            }
        });

        // go to All Tasks page
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

        // Task A button
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

        // Task B button
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
        // Task C button
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

        // go to settings page
        Button gotosettings = MainActivity.this.findViewById(R.id.settingsbtn);
        gotosettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingspage = new Intent(MainActivity.this, SettingsPage.class);
                MainActivity.this.startActivity(settingspage);

            }
        });


        // display the user's username in the homepage

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
             String storeUserName = preferences.getString("username", "user");
             if(userName ==null){
                 homeuser.setText(storeUserName +"'s page");
             }





//        ArrayList<Tasks> tasks = new ArrayList<>();
//        tasks.add(new Tasks("Task one", description, "assigned"));
//        tasks.add(new Tasks("Task two", description, "in progress"));
//        tasks.add(new Tasks("Task three", description, "new"));
//        tasks.add(new Tasks("Task four", description, "complete"));



//        databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                List<Task> taskList = AppDatabase.getDatabase(getApplicationContext()).taskDao().getAll();
//                adapter = new Adapter(MainActivity.this,taskList);
//                recyclerView.setAdapter(adapter);
//
//            }
//        });




//        List<Tasks> tasks = AppDatabase.getDatabase(getApplicationContext()).tasksDao().getAll();
        RecyclerView taskList = findViewById(R.id.recyclerView);

//        databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {


        // replace with amplify
             List<Tasks> tasks = AppDatabase.getDatabase(getApplicationContext()).tasksDao().getAll();



        taskList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        taskList.setAdapter(new TaskAdapter(MainActivity.this,tasks));

        // room-amplify
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
//                        tasks.add(newTask);
//
//                        taskList.setAdapter(new TaskAdapter(MainActivity.this,tasks));
//
//
//                        Log.i("Tutorial", "==== Todo ====");
//                        Log.i("Tutorial", "Name: " + todo.getTitle());
//                    }
//                },
//                failure -> Log.e("Tutorial", "Could not query DataStore", failure)
//        );


//            }
//        });




//        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
//                AppDatabase.class, "tasksdb").build();

//        TasksDao tasksDao = db.tasksDao();
//        List<Tasks> tasksList = tasksDao.getAll();
//
//        RecyclerView taskList = findViewById(R.id.recyclerView);
//        taskList.setLayoutManager(new LinearLayoutManager(this));
//        taskList.setAdapter(new TaskAdapter(   this,tasksList));


    }


//    @RequiresApi(api = Build.VERSION_CODES.Q)
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == AWSCognitoAuthPlugin.WEB_UI_SIGN_IN_ACTIVITY_CODE) {
//            Amplify.Auth.handleWebUISignInResponse(data);
//        }
//
        // how to upload the files of a specific type --> use uri insted of copying the file yourself
//        if(requestCode ==9999){
//            File file = new File(getApplicationContext().getFilesDir(), "uploads");
//            try{
//                InputStream inputStream = getContentResolver().openInputStream(data.getData());
//                FileUtils.copy(inputStream, new FileOutputStream(file));
//                //the key will be the name of the file that you willl be getting form the intent
//                uploadfile(file,"thisIsMyKey");
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }




//    // here use intent.pic
//    public void getFileFromMobileStorage(){
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("*/*");
//        startActivityForResult(intent,9999);
//    }
//    // upload file
//    public void uploadfile(File file, String fileName){
//        Amplify.Storage.uploadFile(
//                fileName,
//                file,
//                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
//                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
//        );
//    }


}