package com.example.project;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    String ip = "192.168.43.10";
    String classs = "net.sourceforge.jtds.jdbc.Driver";
    String DBName = "ProjectPRM";
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
            Class.forName(classs);
            ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + DBName + ";user=" + username + ";password="
                    + password + ";";
            conn = (Connection) DriverManager.getConnection(ConnURL);
        }
        catch (SQLException se)
        {
            Log.e("safiya", se.getMessage());
        }
        catch (ClassNotFoundException e) {
        }
        catch (Exception e) {
            Log.e("error", e.getMessage());
        }
        return conn;
    }
}
