package com.bluescreen.citizenapp;

public class Asignacionmaterialumnocargarmateria {

    String id,nombre;

    public Asignacionmaterialumnocargarmateria() {


    }

    public Asignacionmaterialumnocargarmateria(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
