package com.example.project;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.project.model.Account;
import com.example.project.model.UserInformation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBContext extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ProjectPRM";

    public DBContext(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "";

        //Table Account
        sqlCreate += "create table Account(\n" +
                "Username nvarchar(200) unique NOT NULL,\n" +
                "[Password] nvarchar(200) NOT NULL,\n" +
                "UserID int identity(1,1) primary key\n" +
                ")\n";
        db.execSQL(sqlCreate);
        //Table UserInformation
        sqlCreate = "";
        sqlCreate += "create table UserInfor(\n" +
                "UserID int references Account(UserID),\n" +
                "FullName nvarchar(200),\n" +
                "[Address] nvarchar(200),\n" +
                "Age int\n" +
                ")";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS UserInfor");
        db.execSQL("DROP TABLE IF EXISTS Account");
        onCreate(db);
    }

    public void addAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Username", account.getUsername());
        values.put("Password", account.getPassword());

        // Inserting Row
        db.insert("Account", null, values);

        // Closing database connection
        db.close();
    }

    public void addAdminAccount() {
        Account account = new Account("admin", "admin");
        addAccount(account);
    }

    public Account getAccount(String username, String password) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String sql = "select * from Account where Username = ? and Password = ?";
            Cursor cursor = db.rawQuery(sql, new String[]{username, password});
            if (cursor.moveToNext()) {
                Account account = new Account();
                account.setUsername(cursor.getString(0));
                account.setPassword(cursor.getString(1));
                return account;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.getMessage().toString();
            return null;
        }
    }

    public Boolean checkUsername(String username) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String sql = "select * from Account where Username = ?";
            Cursor cursor = db.rawQuery(sql, new String[]{username});
            if(cursor.moveToNext()) {
                return true;
            } else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void addUserInformation(UserInformation userInformation) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("UserID", userInformation.getUserID());
        values.put("FullName", userInformation.getFullName());
        values.put("Address", userInformation.getAddress());
        values.put("Age", userInformation.getAge());

        // Inserting Row
        db.insert("UserInfor", null, values);

        // Closing database connection
        db.close();
    }
}
