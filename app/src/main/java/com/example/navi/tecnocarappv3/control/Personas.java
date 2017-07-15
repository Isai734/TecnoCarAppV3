package com.example.navi.tecnocarappv3.control;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Navi on 16/05/2017.
 */

public class Personas {

    public int estado;
    public String mensaje;
    public ArrayList<Persona> persona = new ArrayList<>();

    public Personas(int estado, String mensaje, ArrayList<Persona> persona) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.persona = persona;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ArrayList<Persona> getPersona() {
        return persona;
    }

    public void setPersona(ArrayList<Persona> persona) {
        this.persona = persona;
    }
}
