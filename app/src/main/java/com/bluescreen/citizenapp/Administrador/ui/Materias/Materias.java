package com.bluescreen.citizenapp.Administrador.ui.Materias;

import androidx.annotation.NonNull;

public class Materias {

    String nombre;

    public Materias(String nombre) {
        this.nombre = nombre;
    }

    public Materias() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    @Override
    public String toString() {
        return nombre;
    }
}
