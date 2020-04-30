package com.bluescreen.citizenapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bluescreen.citizenapp.R;
import com.google.android.material.tabs.TabLayout;


public class desafiosTabFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_desafios_tab, container, false);
        MyPagerAdapter myPagerAdapter =
                new MyPagerAdapter(
                        getFragmentManager()
                );
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        viewPager.setAdapter(myPagerAdapter);

        return view;
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment;
            switch (i) {
                case 0:
                    fragment = new desafiosTab1Fragment();
                    break;
                case 1:
                    fragment = new desafiosTab2Fragment();
                    break;
                default:
                    fragment = null;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

    }



}

