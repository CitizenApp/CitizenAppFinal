package com.bluescreen.citizenapp.Profe.SubirAvisos;

    public class SubiravisoModel {

        String titulo,descripcion,fecha;

        public SubiravisoModel() {
        }

        public SubiravisoModel(String titulo, String descripcion, String fecha) {
            this.titulo = titulo;
            this.descripcion = descripcion;
            this.fecha = fecha;
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

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }
    }
