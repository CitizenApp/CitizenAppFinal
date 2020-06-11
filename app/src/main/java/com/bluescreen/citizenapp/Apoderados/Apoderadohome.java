package com.bluescreen.citizenapp.Apoderados;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluescreen.citizenapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Apoderadohome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Apoderadohome extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView apoderad;
    ArrayList<Modelreu> modelreus;
    Adapterreu adapterreu;

    public Apoderadohome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Apoderadohome.
     */
    // TODO: Rename and change types and number of parameters
    public static Apoderadohome newInstance(String param1, String param2) {
        Apoderadohome fragment = new Apoderadohome();
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
        return inflater.inflate(R.layout.fragment_apoderadohome, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        apoderad=getView().findViewById(R.id.idreciclerapo);
        Integer[] imagenes={R.drawable.libross,R.drawable.libross};
        String[] info={"29-03-2020","21-04-2020"};
        modelreus=new ArrayList<>();

        for(int i=0;i<imagenes.length;i++){
            Modelreu m=new Modelreu(imagenes[i],info[i]);
            modelreus.add(m);

        }

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        apoderad.setLayoutManager(linearLayoutManager);
        apoderad.setItemAnimator(new DefaultItemAnimator());
        adapterreu=new Adapterreu(modelreus,getContext());
        apoderad.setAdapter(adapterreu);
    }
}
