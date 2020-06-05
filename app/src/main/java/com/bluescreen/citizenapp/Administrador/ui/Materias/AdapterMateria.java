package com.bluescreen.citizenapp.Administrador.ui.Materias;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bluescreen.citizenapp.Alumnos.ChatAlumnosFragment;
import com.bluescreen.citizenapp.DetallesArchivos.DetallesArchivo;
import com.bluescreen.citizenapp.DocumentosFragment;
import com.bluescreen.citizenapp.Fragmentaula;
import com.bluescreen.citizenapp.MainActivity;
import com.bluescreen.citizenapp.R;
import com.bluescreen.citizenapp.ui.CampusinteractivoModel;
import com.google.firebase.database.DatabaseReference;


import java.util.List;

public class AdapterMateria extends RecyclerView.Adapter<AdapterMateria.MateriasHolder>{

    List<CampusinteractivoModel> materias;
    Context mContext;
    public FragmentManager f_manager;





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

    }



    @Override
    public int getItemCount() {
        return materias.size();
    }



    public class MateriasHolder extends RecyclerView.ViewHolder {

        TextView txtTitulo,kl;
        Button b;
        ImageView m;
        private FragmentManager fm;
        ChatAlumnosFragment mm;


        public MateriasHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTitulomateria);
            //m=itemView.findViewById(R.id.imgmess);
           b=itemView.findViewById(R.id.b);

           b.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                  // Toast.makeText(mContext,"Materia : "+materias.get(getAdapterPosition()).getId(),Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(mContext, DetallesArchivo.class);
                   intent.putExtra("ID",materias.get(getAdapterPosition()).getId());
                   mContext.startActivity(intent);
               }

           });




        }




    }




}
