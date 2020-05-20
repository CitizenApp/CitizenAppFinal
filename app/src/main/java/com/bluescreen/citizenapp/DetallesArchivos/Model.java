package com.bluescreen.citizenapp.DetallesArchivos;

import androidx.annotation.NonNull;

public class Model {

    String titulo,url;

    public Model(String titulo, String url) {
        this.titulo = titulo;
        this.url = url;
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

    @NonNull
    @Override
    public String toString() {
        return titulo;
    }
}
