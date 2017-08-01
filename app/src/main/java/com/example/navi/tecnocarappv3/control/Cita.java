package com.example.navi.tecnocarappv3.control;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Isai on 29/07/2017.
 */

public class Cita {
    public String id;
    public String title;
    public String body;
    public String url;
    @SerializedName("class")
    public String clase;
    public String start;
    public String end;
    public String inicio_normal;
    public String final_normal;
    public String auto_placa;
    public String empresa_clave;
    public String cliente_clave;
    public String status;
    public String placa;
    public String marca;
    public String modelo;
    public String color;
    public String anio;
    public String transmision;


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
        this.color = color;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public Cita(String id, String title, String body, String url, String clase, String start, String end, String inicio_normal, String final_normal, String auto_placa, String empresa_clave, String cliente_clave, String status, String placa, String marca, String modelo, String color, String anio, String transmision) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.url = url;
        this.clase = clase;
        this.start = start;
        this.end = end;
        this.inicio_normal = inicio_normal;
        this.final_normal = final_normal;
        this.auto_placa = auto_placa;
        this.empresa_clave = empresa_clave;
        this.cliente_clave = cliente_clave;
        this.status = status;
        this.placa = placa;

        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.anio = anio;
        this.transmision = transmision;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getInicio_normal() {
        return inicio_normal;
    }

    public void setInicio_normal(String inicio_normal) {
        this.inicio_normal = inicio_normal;
    }

    public String getFinal_normal() {
        return final_normal;
    }

    public void setFinal_normal(String final_normal) {
        this.final_normal = final_normal;
    }

    public String getAuto_placa() {
        return auto_placa;
    }

    public void setAuto_placa(String auto_placa) {
        this.auto_placa = auto_placa;
    }

    public String getEmpresa_clave() {
        return empresa_clave;
    }

    public void setEmpresa_clave(String empresa_clave) {
        this.empresa_clave = empresa_clave;
    }

    public String getCliente_clave() {
        return cliente_clave;
    }

    public void setCliente_clave(String cliente_clave) {
        this.cliente_clave = cliente_clave;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
