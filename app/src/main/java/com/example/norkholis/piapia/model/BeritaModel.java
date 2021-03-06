package com.example.norkholis.piapia.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BeritaModel {

    @SerializedName("id_berita")
    @Expose
    private String idBerita;
    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("isi")
    @Expose
    private String isi;
    @SerializedName("gambar")
    @Expose
    private String gambar;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("tag")
    @Expose
    private String tag;

    public String getIdBerita() {
        return idBerita;
    }

    public void setIdBerita(String idBerita) {
        this.idBerita = idBerita;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
