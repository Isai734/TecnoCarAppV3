package com.example.navi.tecnocarappv3.control;

/**
 * Created by Isai on 15/07/2017.
 */

public class Orden {

    public String numero_orden;
    public String placa;
    public String marca;
    public String modelo;
    public String nombre;
    public String apellido_paterno;
    public String apellido_materno;
    public String mano_obra;
    public String total;
    public String estatus;
    public String cliente_clave;

    public Orden(String numero_orden, String placa, String marca, String modelo, String nombre, String apellido_paterno, String apellido_materno, String mano_obra, String total, String estatus, String cliente_clave) {
        this.numero_orden = numero_orden;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.mano_obra = mano_obra;
        this.total = total;
        this.estatus = estatus;
        this.cliente_clave = cliente_clave;
    }

    public String getNumero_orden() {
        return numero_orden;
    }

    public void setNumero_orden(String numero_orden) {
        this.numero_orden = numero_orden;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getMano_obra() {
        return mano_obra;
    }

    public void setMano_obra(String mano_obra) {
        this.mano_obra = mano_obra;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getCliente_clave() {
        return cliente_clave;
    }

    public void setCliente_clave(String cliente_clave) {
        this.cliente_clave = cliente_clave;
    }
}
