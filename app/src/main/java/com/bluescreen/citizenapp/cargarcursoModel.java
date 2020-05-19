package com.bluescreen.citizenapp;

public class cargarcursoModel {
    String nombrecurso,id;

    public cargarcursoModel() {
    }

    public cargarcursoModel(String nombrecurso, String id) {
        this.nombrecurso = nombrecurso;
        this.id = id;
    }

    public String getNombrecurso() {
        return nombrecurso;
    }

    public void setNombrecurso(String nombrecurso) {
        this.nombrecurso = nombrecurso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String toString(){
        return id;

    }

}
