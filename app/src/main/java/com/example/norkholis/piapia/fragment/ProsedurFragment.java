package com.example.norkholis.piapia.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.norkholis.piapia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProsedurFragment extends Fragment {


    public ProsedurFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prosedur, container, false);
    }

}
