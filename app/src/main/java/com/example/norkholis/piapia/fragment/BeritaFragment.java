package com.example.norkholis.piapia.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.norkholis.piapia.DashActivity;
import com.example.norkholis.piapia.R;
import com.example.norkholis.piapia.adapter.BeritaAdapter;
import com.example.norkholis.piapia.api.APIClient;
import com.example.norkholis.piapia.model.BeritaModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeritaFragment extends Fragment {
    private BeritaAdapter BA;
    private APIClient apiClient = new APIClient();
    private Call<List<BeritaModel>> apiCall;
    private RecyclerView RV;

    public BeritaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_berita, container, false);
        apiCall = apiClient.getService().getDataBerita();
        apiCall.enqueue(new Callback<List<BeritaModel>>(){

            @Override
            public void onResponse(Call<List<BeritaModel>> call, Response<List<BeritaModel>> response) {
                if (response.isSuccessful()){
                    List<BeritaModel> listDataTimbangan = response.body();
                    RV = (RecyclerView)view.findViewById(R.id.rv_berita);
                    RV.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
                    RV.setLayoutManager(new LinearLayoutManager(getContext()));
                    BA = new BeritaAdapter(getContext());
                    BA.setData(listDataTimbangan);
                    RV.setAdapter(BA);
                }
            }

            @Override
            public void onFailure(Call<List<BeritaModel>> call, Throwable t) {

            }
        });

        return view;
    }

}
