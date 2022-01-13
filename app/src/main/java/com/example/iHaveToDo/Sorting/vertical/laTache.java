package com.example.iHaveToDo.Sorting.vertical;

public class laTache {

    private String mName;
    private boolean estListe;

    public laTache(String name, boolean isVegetarian) {
        mName = name;
        estListe = isVegetarian;
    }

    public String getName() {
        return mName;
    }

    public boolean isVegetarian() {
        return estListe;
    }
}
