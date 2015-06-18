package com.example.jakob.foxme.Backend;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by Andi on 21.05.2015.
 */
public class AnzeigenServiceImpl {

    public ArrayList<String> fetchAnzeigentxt(List<Anzeige> anzeigenListe) throws Exception { //liefert eine Liste mit den Anzeigentexten zur�ck
        Anzeige anzeige;
        ArrayList<String> text = new ArrayList<String>();
        for (int i = 0; i < anzeigenListe.size(); i++) {
            anzeige = anzeigenListe.get(i);
            text.add(anzeige.getAnzeigentxt());
        }
        return nodubplicate(text);
      }

    public ArrayList<String> nodubplicate(ArrayList<String> liste){
        ArrayList<String> listeohnedup=new ArrayList<String>();
        LinkedHashSet<String> nodup=new LinkedHashSet<String>();
        nodup.addAll(liste);
        listeohnedup.addAll(nodup);
        return listeohnedup;
    }

    public String tagListToString(ArrayList<String> arrayList) {
        String string = "hello_world";//instead of null
        string = TextUtils.join("; ", arrayList);
        return string;
    }

    public ArrayList<String> stringToTagList(String string) {
        String[] zwischenspeicher = TextUtils.split(string, "; ");
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(zwischenspeicher));
        return arrayList;
    }
}
