package com.bluescreen.citizenapp.Administrador.ui.Curso;

import androidx.annotation.NonNull;

public class cursoModel {

    public String nombre,id;

    public cursoModel(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }

    public cursoModel() {
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
