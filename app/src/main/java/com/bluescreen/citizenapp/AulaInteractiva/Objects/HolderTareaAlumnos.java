package com.bluescreen.citizenapp.AulaInteractiva.Objects;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluescreen.citizenapp.R;

public class HolderTareaAlumnos extends RecyclerView.ViewHolder {
    private TextView tituloTarea, descripcionTarea, fechaTarea;
    private Button descargarTarea;

    public HolderTareaAlumnos(@NonNull View itemView) {
        super(itemView);

        tituloTarea = (TextView) itemView.findViewById(R.id.nombreTareaAlumnosCvTv);
        descripcionTarea = (TextView) itemView.findViewById(R.id.DescripcionTareaCvTv);
        fechaTarea = (TextView) itemView.findViewById(R.id.fechaTareaCvTv);
        descargarTarea = (Button) itemView.findViewById(R.id.descargarTareaBtn);

    }

    public TextView getTituloTarea() {
        return tituloTarea;
    }

    public void setTituloTarea(TextView tituloTarea) {
        this.tituloTarea = tituloTarea;
    }

    public TextView getDescripcionTarea() {
        return descripcionTarea;
    }

    public void setDescripcionTarea(TextView descripcionTarea) {
        this.descripcionTarea = descripcionTarea;
    }

    public TextView getFechaTarea() {
        return fechaTarea;
    }

    public void setFechaTarea(TextView fechaTarea) {
        this.fechaTarea = fechaTarea;
    }

    public Button getDescargarTarea() {
        return descargarTarea;
    }

    public void setDescargarTarea(Button descargarTarea) {
        this.descargarTarea = descargarTarea;
    }
}
