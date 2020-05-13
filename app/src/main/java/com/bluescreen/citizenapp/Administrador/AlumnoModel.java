package com.bluescreen.citizenapp.Administrador;

public class AlumnoModel {

    private String nombre,correoelectronico,contraseña,rol;


    public AlumnoModel() {
    }

    public AlumnoModel(String nombre, String correoelectronico, String contraseña, String rol) {
        this.nombre = nombre;
        this.correoelectronico = correoelectronico;
        this.contraseña = contraseña;
        this.rol = rol;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
