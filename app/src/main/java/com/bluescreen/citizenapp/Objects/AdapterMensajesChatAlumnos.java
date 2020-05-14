package com.bluescreen.citizenapp.Objects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluescreen.citizenapp.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterMensajesChatAlumnos extends RecyclerView.Adapter<HolderMensajesChatAlumnos> {
    private List<MensajeChatAlumnos> listaMensajes = new ArrayList<>();
    private Context c;

    public AdapterMensajesChatAlumnos(Context c) {
        this.c = c;
    }

    public void addMensajes(MensajeChatAlumnos m){

        listaMensajes.add(m);
        notifyItemInserted(listaMensajes.size());

    }

    @NonNull
    @Override
    public HolderMensajesChatAlumnos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.cardview_mensajes_chat_alumnos, parent, false);

        return new HolderMensajesChatAlumnos(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMensajesChatAlumnos holder, int position) {
        holder.getNombre().setText(listaMensajes.get(position).getNombre());
        holder.getMensaje().setText(listaMensajes.get(position).getMensaje());
        holder.getHora().setText(listaMensajes.get(position).getHora());



    }

    @Override
    public int getItemCount() {
        return listaMensajes.size();
    }
}
