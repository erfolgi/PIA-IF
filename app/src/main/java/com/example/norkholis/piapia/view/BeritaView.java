package com.example.norkholis.piapia.view;

import android.widget.ProgressBar;

import com.example.norkholis.piapia.model.Datum;

import java.util.List;

public interface BeritaView {
    public void showBerita(List<Datum> items);
    public void showError(String t);

    public void showLoading(ProgressBar progressBar);

    public void hideLoading(ProgressBar progressBar);

}
