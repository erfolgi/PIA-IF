package com.example.norkholis.piapia.view;

import android.view.Menu;

import com.example.norkholis.piapia.model.Datum;
import com.example.norkholis.piapia.model.SkripsiModel;

import java.util.List;

public interface SkripsiView {

    public void showSkripsi(List<SkripsiModel> items);
    public void showError(String t);
}
