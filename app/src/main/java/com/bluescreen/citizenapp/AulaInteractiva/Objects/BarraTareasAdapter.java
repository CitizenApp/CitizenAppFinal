package com.bluescreen.citizenapp.AulaInteractiva.Objects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluescreen.citizenapp.R;

import java.util.ArrayList;
import java.util.List;

public class BarraTareasAdapter extends RecyclerView.Adapter<HolderTareaAlumnos>{
    private List<TareaAlumnos> listaTareas = new ArrayList<>();
    private Context c ;

    public BarraTareasAdapter(Context c) {
        this.c = c;
    }
    public void addTareas (TareaAlumnos t){

        listaTareas.add(t);
        notifyItemInserted(listaTareas.size());


    }

    @NonNull
    @Override
    public HolderTareaAlumnos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.cardview_tareas_alumnos, parent, false);


        return new HolderTareaAlumnos(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderTareaAlumnos holder, int position) {

  holder.getTituloTarea().setText(listaTareas.get(position).getNombreTarea());
  holder.getDescripcionTarea().setText(listaTareas.get(position).getDescripcionTarea());
  holder.getFechaTarea().setText(listaTareas.get(position).getFechaTarea());

    }

    @Override
    public int getItemCount() {
        return listaTareas.size();
    }
}
