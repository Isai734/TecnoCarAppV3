package com.example.navi.tecnocarappv3.model;

import com.example.navi.tecnocarappv3.control.Auto;
import com.example.navi.tecnocarappv3.control.Cita;
import com.example.navi.tecnocarappv3.control.Orden;
import com.example.navi.tecnocarappv3.control.Persona;

import java.util.List;

/**
 * Created by Isai on 13/07/2017.
 */
public class DataStore {
    private static DataStore INSTANCE;
    private List<Auto> autoList;
    private List<Orden> ordenList;
    private List<Cita> citasList;
    private Persona persona;

    private DataStore() {
    }

    public static DataStore getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataStore();
        }
        return INSTANCE;
    }

    public List<Cita> getCitasList() {
        return citasList;
    }

    public void setCitasList(List<Cita> citasList) {
        this.citasList = citasList;
    }

    public void setOrdenList(List<Orden> ordenList) {
        this.ordenList = ordenList;
    }

    public List<Orden> getOrdenList() {
        return ordenList;
    }

    public List<Auto> getAutoList() {
        return autoList;
    }

    public void setAutoList(List<Auto> autoList) {
        this.autoList = autoList;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Persona getPersona() {
        return persona;
    }


}
