package com.bluescreen.citizenapp.Administrador.ui.Materias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluescreen.citizenapp.R;
import com.bluescreen.citizenapp.ui.CampusinteractivoModel;
import com.google.firebase.database.DatabaseReference;


import java.util.List;

public class AdapterMateria extends RecyclerView.Adapter<AdapterMateria.MateriasHolder>{

    List<CampusinteractivoModel> materias;
    Context mContext;




    public AdapterMateria(List<CampusinteractivoModel> materias, Context mContext) {
        this.materias = materias;
        this.mContext = mContext;

    }

    public AdapterMateria(List<Materias> materias) {
    }

    @NonNull
    @Override
    public MateriasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.rowmaterias, parent, false);
        return new MateriasHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MateriasHolder holder, final int position) {
        holder.txtTitulo.setText(materias.get(position).getNombre());
      ;

    }

    @Override
    public int getItemCount() {
        return materias.size();
    }



    public class MateriasHolder extends RecyclerView.ViewHolder {

        TextView txtTitulo,kl;
        Button b;


        public MateriasHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTitulomateria);
           b=itemView.findViewById(R.id.b);

           b.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Toast.makeText(mContext,"Materia : "+materias.get(getAdapterPosition()).getId(),Toast.LENGTH_SHORT).show();
               }
           });




        }




    }



}
