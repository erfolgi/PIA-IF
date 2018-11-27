package com.example.norkholis.piapia.presenter;

import android.util.Log;


import com.example.norkholis.piapia.api.APIPerpus;
import com.example.norkholis.piapia.model.SkripsiModel;
import com.example.norkholis.piapia.view.SkripsiView;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkripsiPresenter {
    private Call<List<SkripsiModel>> apiCall;
    private APIPerpus apiClient;
    private SkripsiView skripsiView;

    //Setter Getter


    public Call<List<SkripsiModel>> getApiCall() {
        return apiCall;
    }

    public void setApiCall(Call<List<SkripsiModel>> apiCall) {
        this.apiCall = apiCall;
    }

    public APIPerpus getApiClient() {
        return apiClient;
    }

    public void setApiClient(APIPerpus apiClient) {
        this.apiClient = apiClient;
    }

    public SkripsiView getSkripsiView() {
        return skripsiView;
    }

    public void setSkripsiView(SkripsiView skripsiView) {
        this.skripsiView = skripsiView;
    }

    //Method
    public void callView(List<SkripsiModel> items){
        skripsiView.showSkripsi(items);
    }

    public void retrieveSkripsi(){
        Log.e("Errorer","1");
        apiCall.enqueue(new Callback<List<SkripsiModel>>(){
            @Override
            public void onResponse(Call<List<SkripsiModel>> call, Response<List<SkripsiModel>> response) {
                Log.e("Errorer","2");
                if (response.isSuccessful()){
                    if (response.body()==null){
                        Log.e("Errorer","NULL!!");
                    }
                    Log.e("Errorer","3");
                    List<SkripsiModel> objek = response.body();

                    //List<Datum> items=objek.getData();
                    Log.e("Errorer",objek.toString());
                    //Collections.reverse(items);

                    callView(objek);
                }
            }

            @Override
            public void onFailure(Call<List<SkripsiModel>> call, Throwable t) {
                Log.e("Errorer",t.toString());
                skripsiView.showError(t.toString());
            }
        });
    }
}
