package com.bluescreen.citizenapp.Objects;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluescreen.citizenapp.R;

public class HolderMensajesChatAlumnos extends RecyclerView.ViewHolder {
    private TextView nombre, mensaje, hora;

    public HolderMensajesChatAlumnos(@NonNull View itemView) {
        super(itemView);

        nombre = (TextView) itemView.findViewById(R.id.nombreusuarioCardviewChatAlumnosTv);
        mensaje = (TextView) itemView.findViewById(R.id.mensajeCardviewChatAlumnosTv);
        hora = (TextView) itemView.findViewById(R.id.horaCardviewChatAlumnosTv);
    }

    public TextView getNombre() {
        return nombre;
    }

    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    public TextView getMensaje() {
        return mensaje;
    }

    public void setMensaje(TextView mensaje) {
        this.mensaje = mensaje;
    }

    public TextView getHora() {
        return hora;
    }

    public void setHora(TextView hora) {
        this.hora = hora;
    }
}
