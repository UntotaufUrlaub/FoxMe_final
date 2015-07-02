package com.example.jakob.foxme.Backend;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jakob.foxme.R;
import com.google.common.collect.Lists;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andi on 22.06.2015.
 */
public class RevieverTask extends AsyncTask<String[], Void, Boolean> {
    public List<Anzeige> liste123 = new ArrayList<>();
    public String filter;
    List<Message> titles;
    List<ResultEntry> entries = Lists.newArrayList();
    ResultEntry now = new ResultEntry();
    ListView konsumentListView;
    ArrayAdapter konsumentArrayAdapter;
    ArrayList<String> konsumentListeInfoTexte = new ArrayList();
    private ProgressDialog dialog;
    private FragmentActivity activity;
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private Context context;
    private String StringLocalTag;
    private ArrayList<String> ListLocalTags;


    public RevieverTask(FragmentActivity activity, String filterbool, String abcd) {
        filter = filterbool;
        this.activity = activity;
        context = activity;
        dialog = new ProgressDialog(context);
        StringLocalTag = abcd;
    }

    protected void onPreExecute() {
        this.dialog.setMessage("Progress start");
        this.dialog.show();
    }

    @Override
    protected void onPostExecute(final Boolean success) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        if (success) {
            AnzeigenServiceImpl anzeigenService = new AnzeigenServiceImpl();
            konsumentListeInfoTexte = anzeigenService.fetchAnzeigentxt(liste123);
            konsumentListView = (ListView) activity.findViewById(R.id.konsument_list_view);
            konsumentArrayAdapter = new ArrayAdapter<String>(activity, R.layout.info_layout, R.id.info_item, konsumentListeInfoTexte);
            konsumentListView.setAdapter(konsumentArrayAdapter);
            Toast.makeText(context, "OK", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
        }
    }

    protected Boolean doInBackground(final String[]... params) {
        Filter gefiltert = new Filter();
        AnzeigenServiceImpl anzeigenService = new AnzeigenServiceImpl();
        ListLocalTags = anzeigenService.stringToTagList(StringLocalTag);
        try {
            Class.forName("com.mysql.jdbc.Driver");            // This will load the MySQL driver, each DB has its own driver
            connect = DriverManager.getConnection("jdbc:mysql://sql5.freemysqlhosting.net/sql579051?" + "user=sql579051&password=zY7!kX3%");          // Setup the connection with the DB
            statement = connect.createStatement();            // Statements allow to issue SQL queries to the database
            resultSet = statement.executeQuery("SELECT DISTINCT Anzeigentext, Adresse, Tags, TimeToLife FROM `FoxMeFinal`"); // Result set get the result of the SQL query
            Log.i("---Select-push :", "start Select-Query");
            int i = 0;
            while (resultSet.next()) {
                Anzeige anzeige = new Anzeige();
                now.adresseProperty = resultSet.getString("Adresse");
                now.anzeigentextProperty = resultSet.getString("Anzeigentext");
                now.tagsProperty = resultSet.getString("Tags");
                now.timetolifeProperty = resultSet.getInt("TimeToLife");
                try {
                    entries.add(now);
                    anzeige.setAnzeigentxt(entries.get(i).anzeigentextProperty);
                    anzeige.setAdresse(entries.get(i).adresseProperty);
                    anzeige.setTags(entries.get(i).tagsProperty);
                    anzeige.setLifetime(entries.get(i).timetolifeProperty);
                    i++;
                    liste123.add(anzeige);
                    if (filter.equals("true")) {
                        liste123 = gefiltert.filterit(liste123, ListLocalTags, Boolean.TRUE);
                    }
                } catch (Exception e) {
                    Log.i("---Select-push-excep :", "abgeschmiert?");
                    e.printStackTrace();
                }
            }
            Log.i("---Select-push :", "end Select-Query");
        } catch (Exception e) {
            try {
                throw e;
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
                Log.i("Class not Found ", "damn it not again");
            } catch (SQLException e1) {
                e1.printStackTrace();//TODO: Warnung am Bildschirm dass die Anzeige nicht abgeschickt wurde aufgrund eines DB-Fehlers
                Log.i("SqlException ", "damn it not again");
            }
        } finally {
            close();
        }
        return true;
    }


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

    public class ResultEntry {
        public String anzeigentextProperty;
        public String adresseProperty;
        public String tagsProperty;
        public int timetolifeProperty;
        public int idProperty;
    }
}

