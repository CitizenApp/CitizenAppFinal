package com.bluescreen.citizenapp.Profe.SubirAvisos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.bluescreen.citizenapp.Administrador.ui.Curso.cursoModel;
import com.bluescreen.citizenapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfeSubirAvisos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfeSubirAvisos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FloatingActionButton agregar;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    CalendarView calendar;
    String fecha;
    EditText titulo,descripcion;
    Button a;
    String idcursoprofe;
    SubiravisoModel subiravisoModel;

    String pp;
    String fechafi;

    public ProfeSubirAvisos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfeSubirAvisos.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfeSubirAvisos newInstance(String param1, String param2) {
        ProfeSubirAvisos fragment = new ProfeSubirAvisos();
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
        return inflater.inflate(R.layout.fragment_profe_subir_avisos, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        subiravisoModel=new SubiravisoModel();

        FirebaseUser fu = FirebaseAuth.getInstance().getCurrentUser() ;
        final String userId = fu.getUid();
        final DatabaseReference dbr = FirebaseDatabase.getInstance().getReference().child("Personal").child(userId).child("CursoAsignado");
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        idcursoprofe=ds.getKey();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference= FirebaseDatabase.getInstance().getReference();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        calendar=getView().findViewById(R.id.calendar);
        //obtener la fecha que seleccione
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                fecha = String.valueOf(dayOfMonth + "/" + (month + 1) + "/" + year);


            }
        });
        StrictMode.setVmPolicy(builder.build());
        calendar = getView().findViewById(R.id.calendar);
        agregar=getView().findViewById(R.id.btn_agregar);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrardialogo(v);
            }
        });
    }

    private void mostrardialogo(View view){

       final AlertDialog.Builder a=new AlertDialog.Builder(getContext());
       View m=getLayoutInflater().inflate(R.layout.dialogosubiraviso,null);
       a.setView(m);
        final Spinner curso=(Spinner)m.findViewById(R.id.spinner2);
       final AlertDialog alertDialog=a.create();
       final EditText titulo2=m.findViewById(R.id.ti);
       final EditText descripcion2=m.findViewById(R.id.d);
       ImageButton aa=(ImageButton) m.findViewById(R.id.button5);
        final List<cursoModel> cursitos=new ArrayList<>();
        FirebaseUser fu = FirebaseAuth.getInstance().getCurrentUser() ;
        final String userId = fu.getUid();
        final DatabaseReference dbr = FirebaseDatabase.getInstance().getReference().child("Personal").child(userId).child("CursoAsignado");
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        cursoModel nombre= ds.getValue(cursoModel.class);
                        nombre.id=ds.getKey();
                        cursitos.add(nombre);
                    }
                    ArrayAdapter<cursoModel> areasAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, cursitos);
                    curso.setAdapter(areasAdapter);
                    curso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            pp=cursitos.get(position).getId();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        aa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference= FirebaseDatabase.getInstance().getReference().child("Cursos").child(pp).child("Avisos");
                subiravisoModel.setTitulo(titulo2.getText().toString());
                subiravisoModel.setDescripcion(descripcion2.getText().toString());
                subiravisoModel.setFecha(fecha);
                databaseReference.push().setValue(subiravisoModel);
            }
        });
        alertDialog.getWindow().setLayout(600, 900);
       alertDialog.setCanceledOnTouchOutside(false);
       alertDialog.show();


    }


}
