package com.example.norkholis.piapia.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SkripsiModel {
    @SerializedName("id_skripsi")
    @Expose
    private String idSkripsi;
    @SerializedName("judul_skripsi")
    @Expose
    private String judulSkripsi;
    @SerializedName("tahun_skripsi")
    @Expose
    private String tahunSkripsi;
    @SerializedName("bidang_minat")
    @Expose
    private String bidangMinat;
    @SerializedName("nama_penulis")
    @Expose
    private String namaPenulis;
    @SerializedName("npm_penulis")
    @Expose
    private String npmPenulis;
    @SerializedName("jurusan_penulis")
    @Expose
    private String jurusanPenulis;
    @SerializedName("kata_kunci")
    @Expose
    private String kataKunci;
    @SerializedName("abstrak")
    @Expose
    private String abstrak;

    public String getIdSkripsi() {
        return idSkripsi;
    }

    public void setIdSkripsi(String idSkripsi) {
        this.idSkripsi = idSkripsi;
    }

    public String getJudulSkripsi() {
        return judulSkripsi;
    }

    public void setJudulSkripsi(String judulSkripsi) {
        this.judulSkripsi = judulSkripsi;
    }

    public String getTahunSkripsi() {
        return tahunSkripsi;
    }

    public void setTahunSkripsi(String tahunSkripsi) {
        this.tahunSkripsi = tahunSkripsi;
    }

    public String getBidangMinat() {
        return bidangMinat;
    }

    public void setBidangMinat(String bidangMinat) {
        this.bidangMinat = bidangMinat;
    }

    public String getNamaPenulis() {
        return namaPenulis;
    }

    public void setNamaPenulis(String namaPenulis) {
        this.namaPenulis = namaPenulis;
    }

    public String getNpmPenulis() {
        return npmPenulis;
    }

    public void setNpmPenulis(String npmPenulis) {
        this.npmPenulis = npmPenulis;
    }

    public String getJurusanPenulis() {
        return jurusanPenulis;
    }

    public void setJurusanPenulis(String jurusanPenulis) {
        this.jurusanPenulis = jurusanPenulis;
    }

    public String getKataKunci() {
        return kataKunci;
    }

    public void setKataKunci(String kataKunci) {
        this.kataKunci = kataKunci;
    }

    public String getAbstrak() {
        return abstrak;
    }

    public void setAbstrak(String abstrak) {
        this.abstrak = abstrak;
    }
}
