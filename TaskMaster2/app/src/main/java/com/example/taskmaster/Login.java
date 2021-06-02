package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText userName = findViewById(R.id.username_Login);
        EditText pass = findViewById(R.id.password_Login);

        Button login = findViewById(R.id.button_Login);




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String usernameData = userName.getText().toString().trim();
                String passData = pass.getText().toString().trim();




                        Amplify.Auth.signIn(
                                usernameData,
                                passData,
                        result -> Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete"),
                        error -> Log.e("AuthQuickstart", error.toString())
                );

                Intent goToMain = new Intent(Login.this, MainActivity.class);
                goToMain.putExtra("userName",usernameData);
                startActivity(goToMain);
            }
        });





    }
}