package com.bluescreen.citizenapp.Administrador.ui.Curso;

import androidx.annotation.NonNull;

public class cursoModel {

    String nombre;

    public cursoModel(String nombre) {
        this.nombre = nombre;
    }

    public cursoModel() {
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
