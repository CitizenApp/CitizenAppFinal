package com.bluescreen.citizenapp.ui;

import androidx.annotation.NonNull;

public class CampusinteractivoModel {

    String nombre,id;

    public CampusinteractivoModel(String nombre, String id) {
        this.nombre = nombre;
        this.id=id;

    }

    public CampusinteractivoModel() {
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
