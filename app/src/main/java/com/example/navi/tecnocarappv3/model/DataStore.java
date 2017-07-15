package com.example.navi.tecnocarappv3.model;

import com.example.navi.tecnocarappv3.control.Autos;

import java.util.List;

/**
 * Created by Isai on 13/07/2017.
 */
public class DataStore {
    private static DataStore INSTANCE;
    private List<Autos.Auto> autoList;

    public static DataStore getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataStore();
        }
        return INSTANCE;
    }

    private DataStore() {
    }

}
