package com.bluescreen.citizenapp.AulaInteractiva.Objects;

public class TareaAlumnos {
    private String nombreTarea, descripcionTarea, fechaTarea;

    public TareaAlumnos() {
    }

    public TareaAlumnos(String nombreTarea, String descripcionTarea, String fechaTarea) {
        this.nombreTarea = nombreTarea;
        this.descripcionTarea = descripcionTarea;
        this.fechaTarea = fechaTarea;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getDescripcionTarea() {
        return descripcionTarea;
    }

    public void setDescripcionTarea(String descripcionTarea) {
        this.descripcionTarea = descripcionTarea;
    }

    public String getFechaTarea() {
        return fechaTarea;
    }

    public void setFechaTarea(String fechaTarea) {
        this.fechaTarea = fechaTarea;
    }
}
