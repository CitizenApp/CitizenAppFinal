package com.bluescreen.citizenapp.Administrador.ui.AgregarApoderados;

public class ModelApoderados {

    String nombre,email,contraseña,rut,idalumno,rol;

    public ModelApoderados(String nombre, String email, String contraseña, String rut, String idalumno, String rol) {
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.rut = rut;
        this.idalumno = idalumno;
        this.rol = rol;
    }

    public ModelApoderados() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(String idalumno) {
        this.idalumno = idalumno;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
