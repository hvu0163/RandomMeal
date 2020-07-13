package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    TextView create_account, forgot_password;

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

    }
}
