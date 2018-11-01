package com.example.norkholis.piapia.api;

import com.example.norkholis.piapia.model.BeritaModel;
import com.example.norkholis.piapia.model.PostBeritaModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APICall {
    @GET("id")
    Call<List<BeritaModel>> getDataBerita();
    @POST("berita/id")
    @FormUrlEncoded
    Call<List<PostBeritaModel>> getDataBeritaId(@Field("id_berita")String id_berita);
}
