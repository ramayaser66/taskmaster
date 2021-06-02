package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;

public class ConfirmSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_sign_up);

        EditText code = findViewById(R.id.verificationcode);

        Button confirm = findViewById(R.id.button_confirm);

        Intent I = getIntent();
        String username = I.getStringExtra("userName");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String confirmationCode = code.getText().toString().trim();


                Amplify.Auth.confirmSignUp(
                        username,
                        confirmationCode,
                        result -> Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete"),
                        error -> Log.e("AuthQuickstart", error.toString())
                );



                Intent goToLogIn = new Intent(ConfirmSignUp.this, Login.class);
                startActivity(goToLogIn);

            }
        });
    }
}