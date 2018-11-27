package com.example.norkholis.piapia.api;

import com.example.norkholis.piapia.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIPerpus {
    private static Retrofit retrofit = null;

    public static Retrofit retrofit(String PERPUS_URL){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(PERPUS_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }return retrofit;
    }
    public APICall getService(){
        return retrofit(BuildConfig.PERPUS_URL).create(APICall.class);
    }
}