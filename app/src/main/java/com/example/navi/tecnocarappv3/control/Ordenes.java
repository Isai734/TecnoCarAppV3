package com.example.navi.tecnocarappv3.control;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Isai on 15/07/2017.
 */

public class Ordenes {
    public  int estado;
    public String mensaje;
    public List<Orden> orden=new LinkedList<>();

    public Ordenes(int estado, String mensaje, List<Orden> orden) {

        this.estado = estado;
        this.mensaje = mensaje;
        this.orden = orden;
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

    public List<Orden> getOrden() {
        return orden;
    }

    public void setOrden(List<Orden> orden) {
        this.orden = orden;
    }
}

