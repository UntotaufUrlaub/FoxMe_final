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
        AnzeigenServiceImpl anzeigenService = new AnzeigenServiceImpl();
        ArrayList<Anzeige> returnList = new ArrayList();              //legt eine leer Liste an, in die die passenden Anzeigen geschrieben werden
        Anzeige anzeige = new Anzeige();
        ArrayList<String> anzeigenTagListe;
        for (int i = 0; i < ungefilterteListe.size(); i++) {
            anzeige = ungefilterteListe.get(i);                       //holt die erste Anzeige aus der Liste
            anzeigenTagListe = anzeigenService.stringToTagList(anzeige.getTags());//holt die Tags
            for (int j = 0; j < anzeigenTagListe.size(); j++) {
                if (anzeigenTagListe.get(j).equals("Alles")) {
                    returnList.add(anzeige);
                }
                for (int k = 0; k < localTag.size(); k++) {
                    if (anzeigenTagListe.get(j).equals(localTag.get(k))) {
                        returnList.add(anzeige);
                    }
                }
            }
        }
        return returnList;
    }
}
