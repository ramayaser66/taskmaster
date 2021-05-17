package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = sharedPreferences.edit();

        Button save = findViewById(R.id.usernameSavebtn);

        save.setOnClickListener((view)->{
            EditText username = findViewById(R.id.editTextTextuserName);
            String user = username.getText().toString();
            edit.putString("username", user);
            edit.apply();

            Intent home = new Intent(SettingsPage.this, MainActivity.class);
            startActivity(home);

            username.setVisibility(View.INVISIBLE);

            TextView saved = findViewById(R.id.textViewusername);
            saved.setText("saved!");
        });
    }
}