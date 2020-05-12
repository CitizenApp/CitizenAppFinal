package com.bluescreen.citizenapp.Administrador;

public class ProfesorModel {

    private String nombre,correoelectronico,rut;

    public ProfesorModel() {
    }

    public ProfesorModel(String nombre, String correoelectronico, String rut) {
        this.nombre = nombre;
        this.correoelectronico = correoelectronico;
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
}
