package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.model.Account;
import com.example.project.model.UserInformation;

public class Register extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void cancel_on_click(View view) {
        onBackPressed();
    }

    public void register_on_click(View view) {
        EditText username, name, password, repass, age, address;
        username = findViewById(R.id.username_txt_register);
        name = findViewById(R.id.name_input);
        password = findViewById(R.id.password_txt_register);
        repass = findViewById(R.id.re_enter_password);
        age = findViewById(R.id.age);
        address = findViewById(R.id.address);

        String pass = password.getText().toString();
        String repassword = repass.getText().toString();
        DBContext db = new DBContext(this);
        if(!pass.equals(repassword))
            Toast.makeText(this,"Wrong re-password",Toast.LENGTH_LONG).show();
        else {
            Boolean check = db.checkUsername(username.getText().toString());
            if(!check) {
                Account account = new Account();
                account.setUsername(username.getText().toString());
                account.setPassword(password.getText().toString());
                db.addAccount(account);

                account = db.getAccount(username.getText().toString(), password.getText().toString());

                UserInformation userInformation = new UserInformation();
                userInformation.setFullName(name.getText().toString());
                userInformation.setAge(Integer.parseInt(age.getText().toString()));
                userInformation.setAddress(address.getText().toString());
                userInformation.setUserID(account.getUserID());
                db.addUserInformation(userInformation);
                Toast.makeText(this,"ss",Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(this,"Username existed",Toast.LENGTH_LONG).show();
        }
    }
}
