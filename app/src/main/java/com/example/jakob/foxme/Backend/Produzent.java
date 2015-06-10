package com.example.jakob.foxme.Backend;

/**
 * Created by Andi on 27.05.2015.
 */
public class Produzent {
    public Anzeige pushit(String text, String tags, int life, String adresse) {
        Anzeige anzeige = new Anzeige();
        anzeige.setAnzeigentxt(text);
        anzeige.setTags(tags);
        anzeige.setLifetime(life);
        anzeige.setAdresse(adresse);
        return anzeige;
    }

}
