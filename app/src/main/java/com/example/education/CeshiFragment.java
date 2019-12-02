package com.example.education;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class CeshiFragment extends Fragment {

    public static CeshiFragment newInstance() {

        Bundle args = new Bundle();

        CeshiFragment fragment = new CeshiFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CeshiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ceshi, container, false);
    }

}
