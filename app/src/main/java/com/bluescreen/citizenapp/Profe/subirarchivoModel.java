package com.bluescreen.citizenapp.Profe;

public class subirarchivoModel {

    String titulo,url,idprofe;

    public subirarchivoModel(String titulo, String url, String idprofe) {
        this.titulo = titulo;
        this.url = url;
        this.idprofe = idprofe;
    }

    public subirarchivoModel() {
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

    public String getIdprofe() {
        return idprofe;
    }

    public void setIdprofe(String idprofe) {
        this.idprofe = idprofe;
    }
}

