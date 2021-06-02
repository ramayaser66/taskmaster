package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText username = findViewById(R.id.username_signup);
        EditText email = findViewById(R.id.email_signup);
        EditText password = findViewById(R.id.password_signup);

        Button signUp = findViewById(R.id.button_signup);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernameData = username.getText().toString();
                String emailData = email.getText().toString();
                String passwordData = password.getText().toString();

                AuthSignUpOptions options = AuthSignUpOptions.builder()
                        .userAttribute(AuthUserAttributeKey.email(), emailData)
                        .build();
                Amplify.Auth.signUp(usernameData, passwordData, options,
                        result -> Log.i("AuthQuickStart", "Result: " + result.toString()),
                        error -> Log.e("AuthQuickStart", "Sign up failed", error)
                );

                Intent confirmSignUp = new Intent(SignUp.this, ConfirmSignUp.class);
                confirmSignUp.putExtra("userName",usernameData);
                startActivity(confirmSignUp);


            }
        });



    }
}