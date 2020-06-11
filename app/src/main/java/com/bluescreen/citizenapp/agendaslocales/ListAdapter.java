package com.bluescreen.citizenapp.agendaslocales;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bluescreen.citizenapp.R;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Modelc> recordList;

    public ListAdapter(Context context, int layout, ArrayList<Modelc> recordList) {
        this.context = context;
        this.layout = layout;
        this.recordList = recordList;
    }

    @Override
    public int getCount() {
        return recordList.size();
    }

    @Override
    public Object getItem(int i) {
        return recordList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row=view;
        ViewHolder holder=new ViewHolder();
        if(row==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout,null);
            holder.titulo=row.findViewById(R.id.textView);
            holder.descripcion=row.findViewById(R.id.textView2);
            holder.hora=row.findViewById(R.id.textView3);
            holder.fecha=row.findViewById(R.id.textView4);

            row.setTag(holder);

        }
        else{
            holder=(ViewHolder)row.getTag();
        }
        Modelc model=recordList.get(i);

        holder.titulo.setText(model.getTitulo());
        holder.descripcion.setText(model.getDescripcion());
        holder.hora.setText(model.getHora());
        holder.fecha.setText(model.getFecha());


        return row;
    }

    private class ViewHolder{

        TextView titulo,descripcion,hora,fecha;
    }
}