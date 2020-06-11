package com.bluescreen.citizenapp.agendaslocales;

public class Modelc {

int id;
    String titulo;
    String descripcion;
    String hora;
    String fecha;

    public Modelc(int id, String titulo, String descripcion, String hora, String fecha) {
        this.id=id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.hora = hora;
        this.fecha = fecha;
    }


    public Modelc() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
