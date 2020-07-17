package com.example.project;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    String ip = "192.168.60.118";
    String classs = "net.sourceforge.jtds.jdbc.Driver";
    String DBName = "QUANGHANHABC";
    String username = "sa";
    String password = "sa";

    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL;
        try {
            Class.forName(classs).newInstance();
            ConnURL = "jdbc:jtds:sqlserver://" + ip + "/"
                    + "databaseName=" + DBName + ";user=" + username + ";password="
                    + password + ";instance=SQLEXPRESS";
            conn = DriverManager.getConnection(ConnURL);
            System.out.println("ok");
        }
        catch (SQLException se)
        {
            Log.e("safiya", se.getMessage());
            System.out.println(se.getMessage());
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            Log.e("error", e.getMessage());

            System.out.println(e.getMessage());
        }
        return conn;
    }
}
