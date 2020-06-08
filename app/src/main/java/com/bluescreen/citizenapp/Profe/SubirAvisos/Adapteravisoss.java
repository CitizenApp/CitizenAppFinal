package com.bluescreen.citizenapp.Profe.SubirAvisos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluescreen.citizenapp.R;

import java.util.List;

public class Adapteravisoss extends RecyclerView.Adapter<Adapteravisoss.AvisosHolder> {
    Context mContext;
    List<AvisosModel> docus;

    public Adapteravisoss(Context mContext, List<AvisosModel> docus) {
        this.mContext = mContext;
        this.docus = docus;
    }

    @NonNull
    @Override
    public AvisosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.xmlavisos, parent, false);
        return new AvisosHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AvisosHolder holder, int position) {
        holder.txtTitulo.setText(docus.get(position).getTitulo());
        holder.descripcion.setText(docus.get(position).getDescripcion());
        holder.fecha.setText(docus.get(position).getFecha());
    }

    @Override
    public int getItemCount() {
        return docus.size();
    }

    public class AvisosHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo,descripcion,fecha;
        public AvisosHolder(@NonNull View itemView) {
            super(itemView);
            fecha=itemView.findViewById(R.id.txtfechaaviso);
            descripcion=itemView.findViewById(R.id.txtd);
            txtTitulo = itemView.findViewById(R.id.txtitul);
        }
    }
}
