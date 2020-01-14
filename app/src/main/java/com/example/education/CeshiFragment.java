package com.example.education;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.router.api.router.WeRouter;


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
        View inflate = inflater.inflate(R.layout.fragment_ceshi, container, false);
        View viewById = inflate.findViewById(R.id.btn222);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeRouter.getInstance().build("native://TestActivity").navigation(getActivity());
            }
        });
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


}
