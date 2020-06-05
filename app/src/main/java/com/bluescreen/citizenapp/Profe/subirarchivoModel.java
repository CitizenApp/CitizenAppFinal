package com.bluescreen.citizenapp.Profe;

public class subirarchivoModel {

    String titulo,url,idprofe,descripcion,fecha;

    public subirarchivoModel(String titulo, String url, String idprofe, String descripcion,String fecha) {
        this.titulo = titulo;
        this.url = url;
        this.idprofe = idprofe;
        this.descripcion = descripcion;
        this.fecha=fecha;
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

    public String getFecha(){
        return fecha;
    }

    public void setFecha(String fecha){
        this.fecha=fecha;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

