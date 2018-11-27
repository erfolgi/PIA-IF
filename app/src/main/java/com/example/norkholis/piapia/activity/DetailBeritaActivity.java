package com.example.norkholis.piapia.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
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
    @BindView(R.id.tanggal_detail) TextView tanggal;
    @BindView(R.id.judul_detail) TextView judul;
    @BindView(R.id.deskripsi_detail) TextView deskripsi;
    @BindView(R.id.gambar_detail) ImageView gambardetail;
    @BindView(R.id.beritaloading) ProgressBar loading;
    @BindView(R.id.loadgambar) ProgressBar loadgambar;
    ImageView gambar;
    private APIClient apiClient = new APIClient();
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);
        ButterKnife.bind(this);
        loading.setVisibility(View.VISIBLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ctx=this;
        EXTRA_ID=getIntent().getStringExtra(EXTRA_ID);
        Call<List<BeritaModel>> apiCallBerita = apiClient.getService().getDataByID(String.valueOf(EXTRA_ID));
        apiCallBerita.enqueue(new Callback<List<BeritaModel>>(){

            @Override
            public void onResponse(Call<List<BeritaModel>> call, Response<List<BeritaModel>> response) {
                if (response.isSuccessful()){
                    Log.d("Error", "1");
                    List<BeritaModel> listData = response.body();
                    Log.d("Error", "2");
                   // List<Datum> listDataBerita= null;
                    if (listData != null) {
                        Log.d("Error", "3");
                        //listDataBerita = listData.get(0).getData();
                        tanggal.setText(listData.get(0).getTanggal());
                        judul.setText(listData.get(0).getJudul());
                        getSupportActionBar().setTitle(listData.get(0).getJudul());
                        String text=listData.get(0).getIsi().replace("<p>","");
                        text=text.replace("</p>","");
                        deskripsi.setText(text);
                        //Spanned sp = Html.fromHtml(listData.get(0).getIsi()).toString();
                        //deskripsi.setText(Html.fromHtml(Html.fromHtml(listData.get(0).getIsi()).toString()));

                        if (!listData.get(0).getGambar().equals("")){
                            loadgambar.setVisibility(View.VISIBLE);
                            try{
                                Glide.with(ctx)
                                        .load("http://piatf.000webhostapp.com/assets/uploads/files/"+listData.get(0).getGambar())
                                        .listener(new RequestListener<Drawable>() {
                                            @Override
                                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                                loadgambar.setVisibility(View.INVISIBLE);
                                                return false;
                                            }

                                            @Override
                                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                                loadgambar.setVisibility(View.INVISIBLE);
                                                return false;
                                            }
                                        }).into(gambardetail);

                            }catch (Exception e){

                            }

                        }


                        Log.d("Testgambar", "piatf.000webhostapp.com/assets/uploads/files/"+listData.get(0).getGambar());

                    }
                    loading.setVisibility(View.INVISIBLE);
                }
            }
            //piatf.000webhostapp.com/assets/uploads/files/namafile
            @Override
            public void onFailure(Call<List<BeritaModel>> call, Throwable t) {
                Log.e("Error","Error: "+t);
                loading.setVisibility(View.INVISIBLE);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return true;

        }

    }
}
