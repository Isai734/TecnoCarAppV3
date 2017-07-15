package com.example.navi.tecnocarappv3.control;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Navi on 16/05/2017.
 */

public class Personas {
    //Nuestra clase receptora debe conincidir en oarametros con el obejeto JSON si no seria imposible poder convertirla

    /**
     * {
     "persona": [
         {
             "clave": "4",
             "nombre": "Mauricio",
             "apellido_paterno": "Cordoba",
             "apellido_materno": "Portillo",
             "telefono": "76879098",
             "direccion": "Tecnologico #70",
             "email": "m_1232@hotmail.com",
             "cp": null,
             "rfc": "-",
             "especialidad": null,
             "tipo": "CLIENTE",
             "status": null
             }
         ],
     "estado": 1,
     "mensaje": "REcursos Obtenidos"
     }
     */

    /**Si ves en ese objeto json hay una varible estado y mensaje dentro del objeto tambien hay un array de personas conotado por las cajitas []
     * y su semejante en java es un obejto {{@link ArrayList}} solo falta insertar el constructor y sus Seter y jeters
     * y listo queda el obeto para recibir el parseo desde json
     */

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

    //y esto entonces no sirve? o por que aqui no se ponen los datos como en el de autos?
    public class Persona {
        /**
         * los nombres de tus variables deben ser iguales que los del objeto jSOn si no no los va reconocer
         * y si no te gusta que se vean asi con gui bajo entonces ocupa el {@SerialisedName} jaja como se escriba
         * <p>
         * entonces esta clase ya la dejo asi, solo cambio el nombre d elas variables para que sean igual al del objeto json
         * si en efcto
         */
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

        public Persona(int clave, String nombre, String apellido_paterno, String apellido_materno, String telefono, String direccion, String email, String cp, String rfc, String especialidad, String tipo, String status) {
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

}
