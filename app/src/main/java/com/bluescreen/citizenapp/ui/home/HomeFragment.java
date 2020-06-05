package com.bluescreen.citizenapp.ui.home;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bluescreen.citizenapp.Objects.Adapter;
import com.bluescreen.citizenapp.Objects.Noticias_home;
import com.bluescreen.citizenapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.INVISIBLE;

public class HomeFragment extends Fragment {
    RecyclerView rv;
    List<Noticias_home> noticias;
    Adapter adapter;
ProgressBar progressBar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference db ;
    private OnFragmentInteractionListener mListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rv = view.findViewById(R.id.recycle_newsHome);
        progressBar=view.findViewById(R.id.progressBar);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
       rv.setHasFixedSize(true);
        firebaseDatabase = FirebaseDatabase.getInstance();
        db = firebaseDatabase.getReference("noticias_home");

    return view;
    }

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onStart() {
        super.onStart();


        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar.setVisibility(INVISIBLE);

                noticias = new ArrayList<>();
                for (DataSnapshot postsnap: dataSnapshot.getChildren()) {
                    Noticias_home noticia = postsnap.getValue(Noticias_home.class);
                    noticias.add(noticia) ;


                }
                adapter = new Adapter(noticias, getActivity());
                rv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
