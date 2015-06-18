package com.example.jakob.foxme.Backend;

import java.util.ArrayList;

/**
 * Created by Andi on 18.06.2015.
 */
public class Filter {
    public ArrayList<String> localTag = new ArrayList();

    public void setLocalTag(ArrayList<String> localTag) {//TODO: change from standard set to setfromprofilefile
        this.localTag = localTag;
    }

    public ArrayList<Anzeige> filterit(ArrayList<Anzeige> ungefilterteListe) {
        ArrayList<Anzeige> returnList = new ArrayList();              //legt eine leer Liste an, in die die passenden Anzeigen geschrieben werden
        Anzeige anzeige = new Anzeige();
        String anzeigenTag;
        ArrayList<String> anzeigenTagListe;
        for (int i = 0; i < ungefilterteListe.size(); i++) {
            anzeige = ungefilterteListe.get(i);                       //holt die erste Anzeige aus der Liste
            anzeigenTag = anzeige.getTags();                          //holt die Tags

        }
        return null;
    }
}
