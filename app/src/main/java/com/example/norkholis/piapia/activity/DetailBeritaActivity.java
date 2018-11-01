package com.example.norkholis.piapia.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.norkholis.piapia.R;
import com.example.norkholis.piapia.api.APIClient;
import com.example.norkholis.piapia.model.BeritaModel;
import com.example.norkholis.piapia.model.Datum;
import com.example.norkholis.piapia.model.PostBeritaModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBeritaActivity extends AppCompatActivity {
    public static String EXTRA_ID="id berita";
    @BindView(R.id.tanggal_detail)
    TextView tanggal;
    @BindView(R.id.judul_detail)
    TextView judul;
    @BindView(R.id.deskripsi_detail)
    TextView deskripsi;
    @BindView(R.id.gambar_detail)
    ImageView gambar;
    private APIClient apiClient = new APIClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);
        ButterKnife.bind(this);
        EXTRA_ID=getIntent().getStringExtra(EXTRA_ID);
        Call<List<PostBeritaModel>> apiCallBerita = apiClient.getService().getDataBeritaId(String.valueOf(EXTRA_ID));
        apiCallBerita.enqueue(new Callback<List<PostBeritaModel>>(){

            @Override
            public void onResponse(Call<List<PostBeritaModel>> call, Response<List<PostBeritaModel>> response) {
                if (response.isSuccessful()){
                    Log.d("Error", "1");
                    List<PostBeritaModel> listData = response.body();
                    Log.d("Error", "2");
                    List<Datum> listDataBerita= null;
                    if (listData != null) {
                        Log.d("Error", "3");
                        listDataBerita = listData.get(0).getData();
                        tanggal.setText(listDataBerita.get(0).getTanggal());
                        judul.setText(listDataBerita.get(0).getJudul());
                        deskripsi.setText(listDataBerita.get(0).getIsi());
                        Log.d("Error", "4");
                    }
                }
            }
            @Override
            public void onFailure(Call<List<PostBeritaModel>> call, Throwable t) {
                Log.e("Error","Error: "+t);
            }
        });
    }
}
