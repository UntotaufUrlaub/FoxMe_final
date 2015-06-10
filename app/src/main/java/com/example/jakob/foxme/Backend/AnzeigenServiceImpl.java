package com.example.jakob.foxme.Backend;

import java.util.ArrayList;
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
}