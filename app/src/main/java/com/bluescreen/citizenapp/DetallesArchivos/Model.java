package com.bluescreen.citizenapp.DetallesArchivos;

import androidx.annotation.NonNull;

public class Model {

    String titulo,url,descripcion,fecha;

    public Model(String titulo, String url,String descripcion,String fecha) {
        this.titulo = titulo;
        this.url = url;
        this.descripcion=descripcion;
        this.fecha=fecha;
    }

    public Model() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFecha(){
        return fecha;
    }

    public void setFecha(String fecha){
        this.fecha=fecha;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @NonNull
    @Override
    public String toString() {
        return titulo;
    }
}
