package com.bluescreen.citizenapp.Administrador.ui.Materias;

import androidx.annotation.NonNull;

public class Materias {

    String nombre,id;

    public Materias(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }

    public Materias() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String toString() {
        return nombre;
    }
}
