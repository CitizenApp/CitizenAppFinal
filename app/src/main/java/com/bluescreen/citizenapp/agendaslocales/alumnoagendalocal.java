package com.bluescreen.citizenapp.agendaslocales;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import com.bluescreen.citizenapp.R;

import java.util.ArrayList;

import static com.bluescreen.citizenapp.agendaslocales.AgregarRecordatorioActivity.mBDSQLite;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link alumnoagendalocal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class alumnoagendalocal extends Fragment implements  CalendarView.OnDateChangeListener, AdapterView.OnItemLongClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CalendarView calendarViewRecordatorio;
    String fecha;

    private SQLiteDatabase db;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    String cadena;
    String dia ;
    ListAdapter mAdapter=null;

    ArrayList<Modelc> mlist;

    public alumnoagendalocal() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment alumnoagendalocal.
     */
    // TODO: Rename and change types and number of parameters
    public static alumnoagendalocal newInstance(String param1, String param2) {
        alumnoagendalocal fragment = new alumnoagendalocal();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alumnoagendalocal, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mlist=new ArrayList<>();
        listView = (ListView) getView().findViewById(R.id.listRecordatorios);
        listView.setOnItemLongClickListener(this);
        mBDSQLite= new BDSQLite(getContext(), "RECORDATORIOS.sqlite", null, 1);
        mBDSQLite.querydata("CREATE TABLE IF NOT EXISTS recordatorios(id INTEGER PRIMARY KEY AUTOINCREMENT, titulo VARCHAR, descripcion VARCHAR, hora VARCHAR, fecha VARCHAR)");
        final Cursor cursor = mBDSQLite.getData("SELECT * FROM recordatorios");
        while (cursor.moveToNext()){
            int id= cursor.getInt(0);
            String titulof=cursor.getString(1);
            String descripcionf=cursor.getString(2);
            String horaf=cursor.getString(3);
            String fechaf=cursor.getString(4);

            mlist.add(new Modelc(id,titulof,descripcionf,horaf,fechaf));
        }

        if(mlist.size()==0){
            //Toast.makeText(this,"vacio",Toast.LENGTH_SHORT).show();
        }
        mAdapter=new ListAdapter(getContext(),R.layout.rowrecord,mlist);
        listView.setAdapter(mAdapter);


        calendarViewRecordatorio = (CalendarView) getView().findViewById((R.id.CalendarioView));
        calendarViewRecordatorio.setOnDateChangeListener(this);
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int i, int i1, int i2) {
        final int dia, mes, anio;
        dia=i;
        mes=i1+1;
        anio=i2;
        fecha = String.valueOf(i2 + "/" + (i1 + 1) + "/" + i);
        Toast.makeText(getContext(), fecha, Toast.LENGTH_SHORT).show();
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        CharSequence []items = new CharSequence[3];
        items[0] = "Agregar Recordatorio";
        items[1] = "Ver Recordatorios";
        items[2] = "Cancelar";

        builder.setTitle("Seleccione una opción")
                .setItems(items , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i==0){
                            Intent intent = new Intent(getContext(), AgregarRecordatorioActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("dia" , dia);
                            bundle.putInt("mes" , mes);
                            bundle.putInt("anio" , anio);
                            intent.putExtras(bundle);
                            startActivity(intent);

                        }else if (i==1){
                            Intent intent = new Intent(getContext(), VerRecordatorioActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("fechaFinal" , fecha);
                            intent.putExtras(bundle);
                            startActivity(intent);

                        } else {
                            return;
                        }

                        //     AlertDialog dialog = builder.create();
                        //   dialog.show();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }
}
