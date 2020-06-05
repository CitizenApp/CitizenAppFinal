package com.bluescreen.citizenapp.DetallesArchivos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bluescreen.citizenapp.Administrador.ui.Materias.AdapterMateria;
import com.bluescreen.citizenapp.AgendaFragment;
import com.bluescreen.citizenapp.AulaInteractiva.BarraTareasFragment;
import com.bluescreen.citizenapp.AvisosFragment;
import com.bluescreen.citizenapp.DocumentosFragment;
import com.bluescreen.citizenapp.Fragmentaula;
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

import static java.security.AccessController.getContext;

public class DetallesArchivo extends AppCompatActivity {
    public String sessionId;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser user;
    RecyclerView recyclerView;
    List<Model> models;
    public String userId;
    Adapterdocumentos ll;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_archivo);
        //List<Fragment> list=new ArrayList<>();
        //list.add(new AgendaFragment());
        //list.add(new BarraTareasFragment());
        //list.add(new AvisosFragment());
       //MyPagerAdapter myPagerAdapter =new MyPagerAdapter(getSupportFragmentManager(),list);
        //ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        //viewPager.setAdapter(myPagerAdapter);
        FirebaseUser fu = FirebaseAuth.getInstance().getCurrentUser() ;
        userId = fu.getUid();
        databaseReference = firebaseDatabase.getInstance().getReference();

        recyclerView=findViewById(R.id.redocu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        models=new ArrayList<>();
         sessionId = getIntent().getStringExtra("ID");

         progressBar=findViewById(R.id.progressBar2);


         //Toast.makeText(getApplicationContext(),sessionId,Toast.LENGTH_SHORT).show();

        DatabaseReference dbr = FirebaseDatabase.getInstance().getReference().child("Personal").child(userId).child("CursoAsignado");
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.INVISIBLE);
                if(dataSnapshot.exists()){

                    for(DataSnapshot ds : dataSnapshot.getChildren()){

                        final String idcurso = ds.getKey();
                        databaseReference.child("Cursos").child(idcurso).child("MateriasAsignadas").child(sessionId).child("pdf").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                    for(DataSnapshot dss : dataSnapshot.getChildren()){
                                        Model l=dss.getValue(Model.class);
                                        models.add(l);
                                }
                                    ll=new Adapterdocumentos(DetallesArchivo.this, models);
                                    ll.notifyDataSetChanged();
                                    recyclerView.setAdapter(ll);


                                }else{
                                    Toast.makeText(getApplicationContext(),"no existe",Toast.LENGTH_LONG);
                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"no existe",Toast.LENGTH_LONG);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public class MyPagerAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> fragmentList;

        public MyPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList=fragmentList;
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

    }
}
