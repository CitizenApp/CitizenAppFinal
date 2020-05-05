package com.bluescreen.citizenapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragmentaula#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragmentaula extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    LinearLayout avisos,documentos,agenda;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragmentaula() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragmentaula.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragmentaula newInstance(String param1, String param2) {
        Fragmentaula fragment = new Fragmentaula();
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

        View view= inflater.inflate(R.layout.fragment_fragmentaula, container, false);
        List<Fragment> list=new ArrayList<>();
        list.add(new AgendaFragment());
        list.add(new DocumentosFragment());
        list.add(new AvisosFragment());
        MyPagerAdapter myPagerAdapter =
                new MyPagerAdapter(
                        getFragmentManager(),list
                );
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        viewPager.setAdapter(myPagerAdapter);
        return view;




    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> fragmentList;

        public MyPagerAdapter(FragmentManager fm,List<Fragment> fragmentList) {
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

    @Override
    public void onStart() {
        super.onStart();
        //agenda=getView().findViewById(R.id.agenda);
        //agenda.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
              //  Intent intent = new Intent(getContext(), AgendaActivity.class);
                //startActivity(intent);

            //}
        //});


    }
}
