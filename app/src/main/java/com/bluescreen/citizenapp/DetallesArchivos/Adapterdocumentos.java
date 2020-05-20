package com.bluescreen.citizenapp.DetallesArchivos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluescreen.citizenapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Adapterdocumentos extends RecyclerView.Adapter<Adapterdocumentos.DocumentosHolder> {
    Context mContext;
    List<Model> docus;

    public Adapterdocumentos(Context mContext, List<Model> docus) {
        this.mContext = mContext;
        this.docus = docus;
    }

    @NonNull
    @Override
    public DocumentosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.rowdocu, parent, false);
        return new DocumentosHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentosHolder holder, final int position) {
        holder.txtTitulo.setText(docus.get(position).getTitulo());
        holder.m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageReference=storage.getReference();
                String id=docus.get(position).getUrl();
                String url =id;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return docus.size();
    }

    public class DocumentosHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo;
        FirebaseStorage storage = FirebaseStorage.getInstance();


        Button m;
        public DocumentosHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.nombreTareaAlumnosCvTv);
            m=itemView.findViewById(R.id.descargarTareaBtn);

            m.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     //Toast.makeText(mContext,"Titulo : "+docus.get(getAdapterPosition()).getUrl(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
