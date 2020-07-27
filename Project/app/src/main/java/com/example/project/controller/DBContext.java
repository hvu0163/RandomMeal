package com.example.project.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.project.model.Account;
import com.example.project.model.Dishes;
import com.example.project.model.Disk;
import com.example.project.model.DiskCategory;
import com.example.project.model.UserInformation;

import java.util.ArrayList;
import java.util.List;

public class DBContext extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ProjectPRM";

    private static int checkCreate = 1;

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
                //Table DiskCategory
        sqlCreate = "";
        sqlCreate += "create table DiskCategory(\n" +
                "CategoryID INTEGER primary key AUTOINCREMENT,\n" +
                "CategoryName nvarchar(200),\n" +
                "Description nvarchar(200)\n" +
                ")";
        db.execSQL(sqlCreate);
        //Table Disk
        sqlCreate = "";
        sqlCreate += "create table Disk(\n" +
                "DiskID INTEGER primary key AUTOINCREMENT,\n" +
                "DiskName nvarchar(200),\n" +
                "Description nvarchar(200),\n" +
                "Content TEXT,\n" +
                "RateAVG float,\n" +
                "CategoryID INTEGER references DiskCategory(CategoryID)\n" +
                ")";
        db.execSQL(sqlCreate);
        //Table RawMaterial
        sqlCreate = "";
        sqlCreate += "create table RawMaterial(\n" +
                "STT INTEGER primary key AUTOINCREMENT,\n" +
                "Content nvarchar(200),\n" +
                "DiskID INTEGER references Disk(DiskID)\n" +
                ")";
        db.execSQL(sqlCreate);
//        addCategory();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS UserInfor");
        db.execSQL("DROP TABLE IF EXISTS Account");
        onCreate(db);
    }

    public static int getCheckCreate() {
        return checkCreate;
    }

    public static void setCheckCreate(int checkCreate) {
        DBContext.checkCreate = checkCreate;
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

    public void addDisk(String string) {
        String[] text = string.split("_");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("DiskName", text[0]);
        values.put("Description", text[2]);
        values.put("Content", text[4]);
        values.put("RateAVG", 0);
        values.put("CategoryID", text[1]);

        db.insert("Disk", null, values);

        Disk disk = getDisk(text[0], text[1]);
        ContentValues values1 = new ContentValues();
        values1.put("Content", text[3]);
        values1.put("DiskID", disk.getDiskID());

        db.insert("RawMaterial", null, values1);
        db.close();
    }

    public Disk getDisk(String DiskName, String Category) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from Disk where DiskName = ? and CategoryID = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{DiskName, Category});
        if (cursor.moveToNext()) {
            Disk disk = new Disk();
            disk.setDiskID(cursor.getInt(0));
            return disk;
        } else {
            return null;
        }
    }

    public void addCategory() {
        SQLiteDatabase db = this.getWritableDatabase();
        //Main disk
        DiskCategory diskCategory = new DiskCategory();
        diskCategory.setCategoryName("Main disk");
        diskCategory.setDescription("");
        ContentValues values = new ContentValues();
        values.put("CategoryName", diskCategory.getCategoryName());
        values.put("Description", diskCategory.getDescription());

        //Soup
        DiskCategory diskCategory1 = new DiskCategory();
        diskCategory1.setCategoryName("Soup");
        diskCategory1.setDescription("");
        ContentValues values1 = new ContentValues();
        values1.put("CategoryName", diskCategory1.getCategoryName());
        values1.put("Description", diskCategory1.getDescription());
        db.insert("DiskCategory", null, values1);

        //Appetizer
        DiskCategory diskCategory2 = new DiskCategory();
        diskCategory2.setCategoryName("Appetizer");
        diskCategory2.setDescription("");
        ContentValues values2 = new ContentValues();
        values2.put("CategoryName", diskCategory2.getCategoryName());
        values2.put("Description", diskCategory2.getDescription());
        db.insert("DiskCategory", null, values2);

        //dessert
        DiskCategory diskCategory3 = new DiskCategory();
        diskCategory3.setCategoryName("dessert");
        diskCategory3.setDescription("");
        ContentValues values3 = new ContentValues();
        values3.put("CategoryName", diskCategory3.getCategoryName());
        values3.put("Description", diskCategory3.getDescription());
        db.insert("DiskCategory", null, values3);
        db.close();
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

    public List<Dishes> getTopDisk() {
        List<Dishes> list = new ArrayList<>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String sql = "select * from Disk order by RateAVG desc LIMIT 9";
            Cursor cursor = db.rawQuery(sql, new String[]{});
            while(cursor.moveToNext()) {
                Disk disk = new Disk();
                disk.setDescription(cursor.getString(2));
                disk.setDiskName(cursor.getString(1));

                Dishes dishes = new Dishes();
                dishes.setName(disk.getDiskName());
                dishes.setUrl(disk.getDescription().trim());

                list.add(dishes);
            }

        } catch (Exception e) {
        }
        return list;
    }

    public Dishes getADisk() {
<<<<<<< HEAD
=======
        List<Dishes> list = new ArrayList<>();
>>>>>>> parent of f837be9... update
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String sql = "select * from Disk order by RateAVG desc LIMIT 9";
            Cursor cursor = db.rawQuery(sql, new String[]{});
            if(cursor.moveToNext()) {
                Disk disk = new Disk();
                disk.setDescription(cursor.getString(2));
                disk.setDiskName(cursor.getString(1));

                Dishes dishes = new Dishes();
                dishes.setName(disk.getDiskName());
                dishes.setUrl(disk.getDescription().trim());

                return dishes;
            }

        } catch (Exception e) {
        }
        return null;
    }
}
