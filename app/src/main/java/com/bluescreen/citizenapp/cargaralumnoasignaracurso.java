package com.bluescreen.citizenapp;

public class cargaralumnoasignaracurso {
    String id,nombre;

    public cargaralumnoasignaracurso(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public cargaralumnoasignaracurso() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString(){
        return nombre;

    }
}
