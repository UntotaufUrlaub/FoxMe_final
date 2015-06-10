package com.example.jakob.foxme.Backend;

/**
 * Created by Andi on 20.05.2015.
 */
public class Anzeige {

    public String anzeigentxt;
    public String tags;
    public String adresse;
    public int lifetime;

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public String getAnzeigentxt() {
        return anzeigentxt;
    }

    public void setAnzeigentxt(String anzeigentxt) {
        this.anzeigentxt = anzeigentxt;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    //private int id;  wird spaeter benoetigt um anzeigen zu identifizieren
}
