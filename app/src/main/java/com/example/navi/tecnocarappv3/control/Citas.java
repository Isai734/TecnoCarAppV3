package com.example.navi.tecnocarappv3.control;

import java.util.ArrayList;

/**
 * Created by Isai on 29/07/2017.
 */

public class Citas {

    public ArrayList<Cita> cita;
    public int estado;
    public String mensaje;

    public Citas(ArrayList<Cita> cita, int estado, String mensaje) {
        this.cita = new ArrayList<>();
        this.cita = cita;
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public ArrayList<Cita> getCita() {
        return cita;
    }

    public void setCita(ArrayList<Cita> cita) {
        this.cita = cita;
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
}
