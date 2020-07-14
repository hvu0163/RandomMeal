package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
