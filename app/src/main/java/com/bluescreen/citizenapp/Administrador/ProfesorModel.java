package com.bluescreen.citizenapp.Administrador;

public class ProfesorModel {

    String nombreprofe;
    String correoprofe;
    int rolprofe;
    String rutprofe;

    public ProfesorModel() {
    }

    public ProfesorModel(String nombreprofe, String correoprofe, int rolprofe, String rutprofe) {
        this.nombreprofe = nombreprofe;
        this.correoprofe = correoprofe;
        this.rolprofe = rolprofe;
        this.rutprofe = rutprofe;
    }

    public String getNombreprofe() {
        return nombreprofe;
    }

    public void setNombreprofe(String nombreprofe) {
        this.nombreprofe = nombreprofe;
    }

    public String getCorreoprofe() {
        return correoprofe;
    }

    public void setCorreoprofe(String correoprofe) {
        this.correoprofe = correoprofe;
    }

    public int getRolprofe() {
        return rolprofe;
    }

    public void setRolprofe(int rolprofe) {
        this.rolprofe = rolprofe;
    }

    public String getRutprofe() {
        return rutprofe;
    }

    public void setRutprofe(String rutprofe) {
        this.rutprofe = rutprofe;
    }
}
