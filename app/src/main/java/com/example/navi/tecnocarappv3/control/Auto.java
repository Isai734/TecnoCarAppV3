package com.example.navi.tecnocarappv3.control;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Isai on 15/07/2017.
 */
public class Auto {


    //placa, marca, modelo, color, anio, transmision, cliente_clave
    public String placa;
    public String marca;
    public String modelo;
    public int anio;
    public String transmision;
    @SerializedName("cliente_clave")
    public int personaId;
    public String color;

    public Auto(int personaId, String placa, String marca, String modelo,String color, int anio, String transmision) {
        this.personaId = personaId;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.anio = anio;
        this.transmision = transmision;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.modelo = color;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }


}