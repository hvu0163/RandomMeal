package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.ui.ForgotPassword;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends AppCompatActivity {
    EditText username, password;
    Button login;
    TextView create_account, forgot_password, textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        create_account = findViewById(R.id.create_account);
        SpannableString content = new SpannableString("Create account");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        create_account.setText(content);

        forgot_password = findViewById(R.id.forgot_password);
        SpannableString content1 = new SpannableString("Forgot password");
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        forgot_password.setText(content1);
    }

    public void register_on_click(View view) {
        Intent intent = new Intent(this, Register.class);
        this.startActivity(intent);
    }

    public void login_on_click(View view) {
        Intent intent = new Intent(this, Home.class);
        this.startActivity(intent);
    }

    public void forgot_password_on_click(View view) {
        Intent intent = new Intent(this, ForgotPassword.class);
        this.startActivity(intent);
    }

}
