package com.example.project.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.model.Account;
import com.example.project.model.UserInformation;

import org.w3c.dom.Text;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }

    public void on_click_submit(View view) {
        EditText username = findViewById(R.id.forgot_username);
        TextView checkusername = findViewById(R.id.validate_username);
        EditText email = findViewById(R.id.forgot_email);
        EditText remail = findViewById(R.id.forgot_email_re_enter);
        Button submit = findViewById(R.id.submit_button);
        Button submit2 = findViewById(R.id.submit_button2);
        DBContext db = new DBContext(this);
        Boolean check = db.checkUsername(username.getText().toString());
        if(!check)
            checkusername.setVisibility(View.VISIBLE);
        else {
            Account account = db.getAccountByUsername(username.getText().toString());
            UserInformation userInformation = db.getUserInforByAccount(account);
            submit.setVisibility(View.INVISIBLE);
            submit2.setVisibility(View.VISIBLE);
            checkusername.setText("Enter your email to get new password");
            checkusername.setVisibility(View.VISIBLE);

            if(userInformation != null) {
                String mailTemp = userInformation.getEmail();
                mailTemp = mailTemp.split("@")[0];
                mailTemp = mailTemp.substring(0, mailTemp.length() - 1);
                mailTemp += "*";
                email.setText(mailTemp);
            }

            email.setVisibility(View.VISIBLE);
            remail.setVisibility(View.VISIBLE);
        }

    }

    public void on_click_submit2(View view) {
        DBContext db = new DBContext(this);
        EditText email = findViewById(R.id.forgot_email);
        EditText remail = findViewById(R.id.forgot_email_re_enter);
        EditText username = findViewById(R.id.forgot_username);
        TextView textView = findViewById(R.id.textView3);
        Account account = db.getAccountByUsername(username.getText().toString());
        UserInformation userInformation = db.getUserInforByAccount(account);
        if(remail.getText().toString().equals(userInformation.getEmail())) {
            db.resetPassword(account);
            textView.setText("Your password was reset to 123");
        } else {
            textView.setText("Wrong email");
        }
    }

    public void on_click_cancel(View view) {
        onBackPressed();
    }
}
