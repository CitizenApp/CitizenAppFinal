package com.bluescreen.citizenapp.Profe;

import android.widget.EditText;

public class usuarios {

    String nombre;
    String correo;
    int tipo;
    String curso;


    public usuarios() {

    }

    public usuarios(String nombre, String correo, int tipo,String curso) {
        this.nombre = nombre;
        this.correo = correo;
        this.tipo = tipo;
        this.curso = curso;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo =tipo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso =curso;
    }

}
