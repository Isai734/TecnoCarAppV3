package com.example.navi.tecnocarappv3.control;

import java.util.ArrayList;

/**
 * pojo de AutosFragment
 */

public class Autos {

    public ArrayList<Auto> autos=new ArrayList<>();

    public Autos(ArrayList<Auto> autos) {
        this.autos = autos;
    }

    public ArrayList<Auto> getAutos() {
        return autos;
    }

    public void setAutos(ArrayList<Auto> autos) {
        this.autos = autos;
    }
}