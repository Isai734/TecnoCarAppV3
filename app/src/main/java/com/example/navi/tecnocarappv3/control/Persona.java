package com.example.navi.tecnocarappv3.control;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Navi on 16/05/2017.
 */

public class Persona {
    @SerializedName("clave")
    public int clave;
    public String nombre;
    public String apellido_paterno;
    public String apellido_materno;
    public String telefono;
    public String direccion;
    public String email;
    public String cp;
    public String rfc;
    public String especialidad;
    public String tipo;
    public String status;
    public String
            usuario,
            contrasenia,
            persona_clave;

    public Persona(int clave, String nombre, String apellido_paterno, String apellido_materno, String telefono, String direccion, String email, String cp, String rfc, String especialidad, String tipo, String status, String usuario, String contrasenia, String persona_clave) {
        this.clave = clave;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.cp = cp;
        this.rfc = rfc;
        this.especialidad = especialidad;
        this.tipo = tipo;
        this.status = status;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.persona_clave = persona_clave;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

