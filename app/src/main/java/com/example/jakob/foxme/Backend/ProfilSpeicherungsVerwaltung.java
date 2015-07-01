package com.example.jakob.foxme.Backend;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Jakob on 18.06.15.
 */
public class ProfilSpeicherungsVerwaltung {
    private Context context;

    public ProfilSpeicherungsVerwaltung(Context c){
        context=c;
    }

    public void save(String eingabe){
        try {
            FileOutputStream fos = context.openFileOutput("Profil.txt",Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);

            try {
                osw.write(eingabe);
                osw.flush();
                osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public String load(){
        String ausgabe="";
        try {
            FileInputStream fis = context.openFileInput("Profil.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            char[] data =new char [100];
            int size;
            try {
                while((size=isr.read(data))>0){
                    String read_data= String.copyValueOf(data,0,size);
                    ausgabe+=read_data;
                    data=new char[100];
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            fis.close();
        } catch (Exception e) {
            Log.i("profil speichern","catch outer exeption-----------------------------------------------------------------------------------------------------");
            e.printStackTrace();
            ausgabe=" ; Männlich; Halal; Alkohol; Fisch;  ; Italienisch; Afrikanisch;  ; Asiatisch;  ;  ;  ";     //standart zustand des Profils
        }
        return ausgabe;
    }
}
