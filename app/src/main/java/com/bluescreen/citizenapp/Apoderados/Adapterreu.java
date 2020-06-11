package com.bluescreen.citizenapp.Apoderados;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bluescreen.citizenapp.R;

import java.util.ArrayList;

public class Adapterreu extends RecyclerView.Adapter<Adapterreu.ViewHolder> {
    ArrayList<Modelreu> modelreus;
    Context context;

    public Adapterreu(ArrayList<Modelreu> modelreus, Context context) {
        this.modelreus = modelreus;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rowreu,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(modelreus.get(position).getLogo());
        holder.textView.setText(modelreus.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return modelreus.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.nn);
            textView=itemView.findViewById(R.id.nn2);
        }
    }
}
