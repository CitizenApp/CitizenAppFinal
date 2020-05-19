package com.bluescreen.citizenapp.Administrador.ui.Materias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluescreen.citizenapp.Administrador.ui.Materias.AdapterMateria;
import com.bluescreen.citizenapp.R;


import java.util.List;

public class AdapterMateria extends RecyclerView.Adapter<AdapterMateria.MateriasHolder> {

    List<Materias> materias;
    Context mContext;

    public AdapterMateria(List<Materias> materias, Context mContext) {
        this.materias = materias;
        this.mContext = mContext;
    }

    public AdapterMateria() {
    }

    public AdapterMateria(List<Materias> materias) {
    }


    @NonNull
    @Override
    public MateriasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.rowmaterias, parent, false);
        MateriasHolder holder=new MateriasHolder(v);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MateriasHolder holder, int position) {
        Materias materiass=materias.get(position);
        holder.txtTitulo.setText(materiass.getNombre());
    }

    @Override
    public int getItemCount() {
        return materias.size();
    }

    public class MateriasHolder extends RecyclerView.ViewHolder {

        TextView txtTitulo;
        public MateriasHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTitulomateria);
        }
    }
}
