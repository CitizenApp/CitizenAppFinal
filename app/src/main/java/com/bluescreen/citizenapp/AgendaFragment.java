package com.bluescreen.citizenapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bluescreen.citizenapp.DetallesArchivos.Model;
import com.bluescreen.citizenapp.Profe.SubirAvisos.Adapteravisoss;
import com.bluescreen.citizenapp.Profe.SubirAvisos.AvisosModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;

import static android.content.Context.MODE_PRIVATE;
import static com.zipow.videobox.confapp.ConfMgr.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgendaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgendaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    LinearLayout lac;
    Boolean firs;
    RecyclerView recyclerView;
    List<AvisosModel> models;

    public String sessionId;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser user;
    public String userId;
    Adapteravisoss adapteravisoss;


    public AgendaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgendaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgendaFragment newInstance(String param1, String param2) {
        AgendaFragment fragment = new AgendaFragment();
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
        return inflater.inflate(R.layout.fragment_agenda, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        models=new ArrayList<>();
        FirebaseUser fu = FirebaseAuth.getInstance().getCurrentUser() ;
        userId = fu.getUid();
        databaseReference = firebaseDatabase.getInstance().getReference();
        recyclerView=getView().findViewById(R.id.recicleravisos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        Boolean isFirstRun = getActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            new MaterialShowcaseView.Builder(getActivity())
                    .setTarget(recyclerView)
                    .setMaskColour(getResources().getColor(R.color.colorAccent))
                    .setDismissText("Entendido")
                    .setShapePadding(130)
                    .setContentText("Bienvenido al Campus Interactivo, Desliza hacia el lado y podras ver tus documentos de cada asignatura")
                    .show();

        }
        getActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();

        DatabaseReference dbr = FirebaseDatabase.getInstance().getReference().child("Personal").child(userId).child("CursoAsignado");
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        final String idcurso = ds.getKey();
                        databaseReference.child("Cursos").child(idcurso).child("Avisos").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){

                                    for(DataSnapshot dss : dataSnapshot.getChildren()){

                                        AvisosModel m=dss.getValue(AvisosModel.class);
                                        models.add(m);

                                    }
                                    adapteravisoss=new Adapteravisoss(getContext(), models);
                                    adapteravisoss.notifyDataSetChanged();
                                    recyclerView.setAdapter(adapteravisoss);
                                    Collections.reverse(models);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });



                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
}
