package com.bluescreen.citizenapp.Objects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bluescreen.citizenapp.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.NoticiasViewHolder>{
    List<Noticias_home> noticias;
    Context mContext;



    public Adapter(List<Noticias_home> noticias, Context mContext) {
        this.noticias = noticias;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public NoticiasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.row_noticias, parent, false);
        return new NoticiasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticiasViewHolder holder, int position) {
        holder.txtTitulo.setText(noticias.get(position).getTitulo());
        holder.txtFecha.setText(noticias.get(position).getFecha());
        holder.txtDescripcion.setText(noticias.get(position).getContenido());
    }

    @Override
    public int getItemCount() {
        return noticias.size();
    }

    public static class NoticiasViewHolder extends RecyclerView.ViewHolder{

        TextView txtTitulo, txtFecha, txtDescripcion;


        public NoticiasViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTituloNoticia);
            txtFecha = itemView.findViewById(R.id.txtFechaNoticia);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcionNoticia);

        }
    }
}
