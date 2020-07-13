package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void cancel_on_click(View view) {
        button = findViewById(R.id.cancel_button);
        Intent intent = new Intent(this, Login.class);
        this.startActivity(intent);
    }

    public void register_on_click(View view) {

    }
}
