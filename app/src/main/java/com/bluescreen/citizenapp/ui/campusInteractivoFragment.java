package com.bluescreen.citizenapp.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bluescreen.citizenapp.Administrador.ui.Materias.AdapterMateria;
import com.bluescreen.citizenapp.R;
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
 * Use the {@link campusInteractivoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class campusInteractivoFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RelativeLayout artes;
   FirebaseUser user;
   String ui;
   TextView ma;
   FirebaseAuth auth;
   FirebaseDatabase firebaseDatabase;

   DatabaseReference databaseReference;


   String idmateria;


   RecyclerView recyclerView;
    List<CampusinteractivoModel> materias;


    AdapterMateria adaptermaterias;

    ProgressBar progressBar;




    public campusInteractivoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment campusInteractivoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static campusInteractivoFragment newInstance(String param1, String param2) {
        campusInteractivoFragment fragment = new campusInteractivoFragment();
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
        return inflater.inflate(R.layout.fragment_campus_interactivo, container, false);

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onStart() {
        super.onStart();



        progressBar=getView().findViewById(R.id.progressBar4);
        recyclerView=getView().findViewById(R.id.recycle_materias);
        materias=new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        FirebaseUser fu = FirebaseAuth.getInstance().getCurrentUser() ;
        final String userId = fu.getUid();
        databaseReference = firebaseDatabase.getInstance().getReference();






        DatabaseReference dbr = FirebaseDatabase.getInstance().getReference().child("Personal").child(userId).child("CursoAsignado");
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.INVISIBLE);
                if(dataSnapshot.exists()){
                    for(DataSnapshot ds : dataSnapshot.getChildren()){{
                        final String idcurso = ds.getKey();

                        databaseReference.child("Cursos").child(idcurso).child("MateriasAsignadas").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                    for(DataSnapshot ds : dataSnapshot.getChildren()){{
                                         idmateria = ds.getKey();
                                        databaseReference.child("Materias").child(idmateria).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                for(DataSnapshot ds : dataSnapshot.getChildren()){

                                                    CampusinteractivoModel noticia = dataSnapshot.getValue(CampusinteractivoModel.class);
                                                    //materias.add(noticia);
                                                    noticia.id=dataSnapshot.getKey();
                                                    materias.add(noticia);

                                                }

                                                adaptermaterias=new AdapterMateria(materias,getContext());
                                                adaptermaterias.notifyDataSetChanged();
                                                recyclerView.setAdapter(adaptermaterias);

                                            }


                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });

                                    }}
                                }




                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //artes.setOnClickListener(new View.OnClickListener() {
           // @Override
            //public void onClick(View v) {
                // Create new fragment and transaction
              //  Fragment someFragment = new Fragmentaula();
                //FragmentTransaction transaction = getFragmentManager().beginTransaction();
                //transaction.replace(R.id.nav_host_fragment, someFragment );
                //transaction.addToBackStack(null);
                //transaction.commit();
            //}
        //});
    }
}
