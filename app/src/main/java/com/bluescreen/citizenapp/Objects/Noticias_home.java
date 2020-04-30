package com.bluescreen.citizenapp.Objects;

public class Noticias_home {
    String titulo;
    String fecha;
    String descripcion;
    String contenido;

    public Noticias_home() {
    }

    public Noticias_home(String titulo, String fecha, String descripcion, String contenido) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.contenido = contenido;
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

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
