package com.example.norkholis.piapia.api;

import com.example.norkholis.piapia.model.BeritaModel;
import com.example.norkholis.piapia.model.KategoriModel;
import com.example.norkholis.piapia.model.PostBeritaModel;
import com.example.norkholis.piapia.model.SkripsiModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APICall {
    @GET("id")
    Call<List<BeritaModel>> getDataBerita();

    @POST("berita/id")
    @FormUrlEncoded
    Call<List<PostBeritaModel>> getDataBeritaId(@Field("id_berita")String id_berita);

    @GET("id")
    Call<List<BeritaModel>> getDataByID(@Query("id_berita") String id_berita);

    //http://piatf.000webhostapp.com/index.php/api/berita/kategoricuy
    @POST("berita/kategoricuy")
    @FormUrlEncoded
    Call<KategoriModel> getDataByKategori(@Field("kategoricuy") String kategoricuy);

    @GET("api.php?jurusan=Teknik%20Informatika")
    Call<SkripsiModel> getSkripsi();

    @GET("api.php?jurusan=Teknik%20Informatika")
    Call<List<SkripsiModel>> getSkripsiList();

    //http://perpusfik.000webhostapp.com/api.php?bidangminat=Software Engineering
    @GET("api.php")
    Call<List<SkripsiModel>> getSkripsiByBidang(@Query("bidangminat") String bidangminat);

}
