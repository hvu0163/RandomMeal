package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends AppCompatActivity {
    TextView username, password, textView;
    Button login;
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

    public void onClick(View view) {
        username = findViewById(R.id.username_txt);
        password = findViewById(R.id.password_txt);
        DBContext db = new DBContext();
        Connection connection = db.CONN();
        String sql = "select * from Account where Username = ? and Password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username.getText().toString());
            ps.setString(2, password.getText().toString());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Toast.makeText(this,"ss",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"ff",Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.toString();
        }
    }
}
