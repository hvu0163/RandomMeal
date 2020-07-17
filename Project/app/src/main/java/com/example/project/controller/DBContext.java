package com.example.project.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.project.model.Account;
import com.example.project.model.UserInformation;

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
                "UserID INTEGER primary key AUTOINCREMENT,\n" +
                "Username nvarchar(200) unique NOT NULL,\n" +
                "[Password] nvarchar(200) NOT NULL\n" +
                ")\n";
        db.execSQL(sqlCreate);
        //Table UserInformation
        sqlCreate = "";
        sqlCreate += "create table UserInfor(\n" +
                "UserID INTEGER references Account(UserID),\n" +
                "FullName nvarchar(200),\n" +
                "[Email] nvarchar(200),\n" +
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
                account.setUserID(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setPassword(cursor.getString(2));
                return account;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.getMessage().toString();
            return null;
        }
    }

    public Account getAccountByUsername(String username) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String sql = "select * from Account where Username = ?";
            Cursor cursor = db.rawQuery(sql, new String[]{username});
            if (cursor.moveToNext()) {
                Account account = new Account();
                account.setUserID(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setPassword(cursor.getString(2));
                return account;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.getMessage().toString();
            return null;
        }
    }

    public UserInformation getUserInforByAccount (Account account) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String sql = "select * from UserInfor where UserID = ?";
            Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(account.getUserID())});
            if(cursor.moveToNext()) {
                UserInformation userInformation = new UserInformation();
                userInformation.setUserID(account.getUserID());
                userInformation.setFullName(cursor.getString(1));
                userInformation.setEmail(cursor.getString(2));
                userInformation.setAge(cursor.getInt(3));
                return userInformation;
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
        values.put("Email", userInformation.getEmail());
        values.put("Age", userInformation.getAge());

        // Inserting Row
        db.insert("UserInfor", null, values);

        // Closing database connection
        db.close();
    }

    public void resetPassword(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Password", "123");
        db.update("Account", values, "Username = ?", new String[]{account.getUsername()});
    }
}
