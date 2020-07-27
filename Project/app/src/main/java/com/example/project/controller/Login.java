package com.example.project.controller;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;
import com.example.project.model.Account;
import com.google.android.material.navigation.NavigationView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Login extends AppCompatActivity {
    EditText username, password;
    Button login;
    TextView create_account, forgot_password, textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBContext db = new DBContext(this);
        if(db.getCheckCreate() == 1) {
            db.addAdminAccount();
            db.addCategory();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open("insert.txt")));
                String line = bufferedReader.readLine();
                while (line != null) {
                    db.addDisk(line);
                    line = bufferedReader.readLine();
                }
                db.setCheckCreate(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
        DBContext db = new DBContext(this);
        username = findViewById(R.id.username_txt);
        password = findViewById(R.id.password_txt);

        Account account = db.getAccount(username.getText().toString(), password.getText().toString());
        if (account != null) {
            Toast.makeText(this, "ss", Toast.LENGTH_LONG).show();
            db.setAccount(account);
            Intent intent = new Intent(this, Home.class);
            this.startActivity(intent);
            if(account.getUsername().equals("admin")) {
                NavigationView navigationView = findViewById(R.id.nav_view);
                Menu menu = navigationView.getMenu();
                String itemName = "Add dish";
                menu.add(R.id.nav_view,Menu.NONE,Menu.NONE,itemName);
            }
        } else {
            Toast.makeText(this, "ff", Toast.LENGTH_LONG).show();
        }
    }

    public void forgot_password_on_click(View view) {
        Intent intent = new Intent(this, ForgotPassword.class);
        this.startActivity(intent);

    }

}
