package com.example.jakob.foxme.Backend;

import android.os.AsyncTask;
import android.util.Log;

import com.example.jakob.foxme.MainActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Andi Goodfella on 11.05.2015.
 */
public class SenderTask extends AsyncTask {

    public List<Anzeige> liste123 = new ArrayList<>();
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

    @Override
    protected List<Anzeige> doInBackground(Object[] params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");            // This will load the MySQL driver, each DB has its own driver
            connect = DriverManager.getConnection("jdbc:mysql://sql5.freemysqlhosting.net/sql579051?" + "user=sql579051&password=zY7!kX3%");          // Setup the connection with the DB
            statement = connect.createStatement();            // Statements allow to issue SQL queries to the database
            //funktioniert, DO NOT ALTER
            preparedStatement = connect.prepareStatement("INSERT INTO `FoxMeFinal` VALUES  (?, ?, ?, ? , ?)");
                preparedStatement.setString(1, (String) params[1]);                     //Anzeigentext
                preparedStatement.setString(2, (String) params[2]);                     //Anzeigenadr
                preparedStatement.setString(3, (String) params[3]);                     //Taglist, als String seperated by ; or #
                preparedStatement.setInt(4, 90);                                        //int TimeToLife
                preparedStatement.setInt(5, 1);                                         //id needs to be incremented
                preparedStatement.executeUpdate();
        } catch (Exception e) {
            try {
                throw e;
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
                Log.i("Class not Found ","damn it not again");
            } catch (SQLException e1) {
                e1.printStackTrace();//TODO: Warnung am Bildschirm dass die Anzeige nicht abgeschickt wurde aufgrund eines DB-Fehlers
                //Toast.makeText(null, "Die Anzeige konnte nicht abgeschickt werden.s", Toast.LENGTH_LONG).show();
                Log.i("SqlException ", "damn it not again");
            }
        } finally {
            close();
        }
        MainActivity.liste = liste123;
        for(int i=0;i<liste123.size();i++){
            String testlogger=liste123.get(i).getAnzeigentxt();
            Log.i("--Ausgabenliste :", testlogger);
        }
        return liste123;
    }
}