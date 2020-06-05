package com.bluescreen.citizenapp.Administrador.ui.AsignarCursoAProfesor;

public class cursoaprofesorModel {

    String idcurso,nombre;

    public cursoaprofesorModel(String idcurso, String nombre) {
        this.idcurso = idcurso;
        this.nombre = nombre;
    }

    public cursoaprofesorModel() {
    }

    public String getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(String idcurso) {
        this.idcurso = idcurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
