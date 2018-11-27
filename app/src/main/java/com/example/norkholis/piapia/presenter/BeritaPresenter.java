package com.example.norkholis.piapia.presenter;

import android.util.Log;

import com.example.norkholis.piapia.api.APIClient;
import com.example.norkholis.piapia.model.Datum;
import com.example.norkholis.piapia.model.KategoriModel;
import com.example.norkholis.piapia.view.BeritaView;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeritaPresenter {
    private Call<KategoriModel> apiCall;
    private APIClient apiClient;
    private BeritaView beritaView;

    public Call<KategoriModel> getApiCall() {
        return apiCall;
    }

    public void setApiCall(Call<KategoriModel> apiCall) {
        this.apiCall = apiCall;
    }

    public APIClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(APIClient apiClient) {
        this.apiClient = apiClient;
    }

    public BeritaView getBeritaView() {
        return beritaView;
    }

    public void setBeritaView(BeritaView beritaView) {
        this.beritaView = beritaView;
    }

    public void callView(List<Datum> items){
        beritaView.showBerita(items);
    }


    public void getBerita(){
        //lateinit var items:MutableList
        //final List<Datum> items;
        Log.e("Errorer","1");
        apiCall.enqueue(new Callback<KategoriModel>(){
            @Override
            public void onResponse(Call<KategoriModel> call, Response<KategoriModel> response) {
                Log.e("Errorer","2");
                if (response.isSuccessful()){
                    if (response.body()==null){
                        Log.e("Errorer","NULL!!");
                    }
                    Log.e("Errorer","3");
                    KategoriModel objek = (KategoriModel) response.body();

                    List<Datum> items=objek.getData();
                    Log.e("Errorer",items.toString());
                    Collections.reverse(items);
                    callView(items);
                }
            }

            @Override
            public void onFailure(Call<KategoriModel> call, Throwable t) {
                Log.e("Errorer",t.toString());
                beritaView.showError(t.toString());
            }
        });
    }


}
