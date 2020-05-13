package com.bluescreen.citizenapp;

public class AsignarmateriasalumnosModel {

String nombrealumno,id;

    public AsignarmateriasalumnosModel() {
    }

    public AsignarmateriasalumnosModel(String nombrealumno, String id) {
        this.nombrealumno = nombrealumno;
        this.id = id;
    }

    public String getNombrealumno() {
        return nombrealumno;
    }

    public void setNombrealumno(String nombrealumno) {
        this.nombrealumno = nombrealumno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return  id;
    }
}
