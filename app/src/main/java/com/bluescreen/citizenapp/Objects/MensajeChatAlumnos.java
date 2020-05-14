package com.bluescreen.citizenapp.Objects;

public class MensajeChatAlumnos {

    private String mensaje, nombre, tipo_mensaje, hora;

    public MensajeChatAlumnos() {
    }

    public MensajeChatAlumnos(String mensaje, String nombre, String tipo_mensaje, String hora) {
        this.mensaje = mensaje;
        this.nombre = nombre;
        this.tipo_mensaje = tipo_mensaje;
        this.hora = hora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_mensaje() {
        return tipo_mensaje;
    }

    public void setTipo_mensaje(String tipo_mensaje) {
        this.tipo_mensaje = tipo_mensaje;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
