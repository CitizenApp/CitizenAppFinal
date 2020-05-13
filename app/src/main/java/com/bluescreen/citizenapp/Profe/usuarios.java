package com.bluescreen.citizenapp.Profe;

import android.widget.EditText;

public class usuarios {

    String nombre;
    String correo;
    int rol;
    String rut;


    public usuarios() {

    }

    public usuarios(String nombre, String correo, int rol, String rut) {
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
}
