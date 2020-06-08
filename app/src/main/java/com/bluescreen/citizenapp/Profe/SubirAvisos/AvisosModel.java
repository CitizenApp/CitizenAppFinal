package com.bluescreen.citizenapp.Profe.SubirAvisos;

public class AvisosModel {
    String titulo;
    String fecha;
    String descripcion;

    public AvisosModel(String titulo, String fecha, String descripcion) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public AvisosModel() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
