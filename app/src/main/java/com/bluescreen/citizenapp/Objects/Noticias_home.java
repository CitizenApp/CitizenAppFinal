package com.bluescreen.citizenapp.Objects;

public class Noticias_home {
    String titulo;
    String url;
    String fecha;
    String contenido;

    public Noticias_home() {
    }

    public Noticias_home(String titulo, String url, String fecha, String contenido) {
        this.titulo = titulo;
        this.url=url;
        this.fecha = fecha;
        this.contenido = contenido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url=url;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
