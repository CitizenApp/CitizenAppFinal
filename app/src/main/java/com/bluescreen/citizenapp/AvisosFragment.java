package com.bluescreen.citizenapp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.LocaleList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AvisosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class AvisosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    CompactCalendarView compactCalendarView;
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MMMM-yyyy", Locale.getDefault());



    public AvisosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        final CompactCalendarView compactCalendarView = (CompactCalendarView) getView().findViewById(R.id.compactcalendar_view);

        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        Event ev1 = new Event(Color.GREEN, 1588283160, "Some extra data that I want to store.");
        compactCalendarView.addEvent(ev1);
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context=getContext();
                if(dateClicked.toString().compareTo("Thursday, 30 April 2020 10:40:42")==0){
                    Toast.makeText(getContext(),"holi",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"no hay ningun evento",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

            }
        });
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AvisosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AvisosFragment newInstance(String param1, String param2) {
        AvisosFragment fragment = new AvisosFragment();
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
        return inflater.inflate(R.layout.fragment_avisos, container, false);
    }
}
