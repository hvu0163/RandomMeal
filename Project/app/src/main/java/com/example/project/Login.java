package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void on_click_register(View view) {
        textView = findViewById(R.id.create_account);
        Intent intent = new Intent(this, Register.class);
        this.startActivity(intent);
//        Toast.makeText(this,"aa",Toast.LENGTH_SHORT).show();
    }
}
