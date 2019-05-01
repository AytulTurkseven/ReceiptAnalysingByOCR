package com.example.furka.retrofit;

import android.telecom.Call;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("upload.php")
    retrofit2.Call<ImageClass>uploadImage(@Field("image") String image,@Query("kullaniciadi") String kullaniciadi);

    @GET("register.php")
    retrofit2.Call<kullanici> kaydol(@Query("ad") String Ad,@Query("soyad") String Soyad,@Query("kullaniciadi") String Kullaniciadi,@Query("sifre") String Sifre);

    @GET("login.php")
    retrofit2.Call<kullanici> girisyap(@Query("kullaniciadi") String Kullaniciadi,@Query("sifre") String Sifre);

    @GET("r.php")
    retrofit2.Call<List<ImageRecyclerClass>> rcek(@Query("kullaniciadi") String kullaniciadi,@Query("tarih") String tarih);

    @GET("ocr.php")
    retrofit2.Call<List<maliyetclass>> rapor(@Query("kullaniciadi") String kullaniciadi,@Query("tarih") String tarih);

    @GET("ocrolmayan.php")
    retrofit2.Call<List<maliyetclass>> raporolmayan(@Query("kullaniciadi") String kullaniciadi,@Query("tarih") String tarih);

    @GET("urunkayit.php")
    retrofit2.Call<ekle> urunekle(@Query("urunadi") String urunadi,@Query("kategoriid") String kategoriid);

    @GET("delete.php")
    retrofit2.Call<ImageClass> resimsil(@Query("image") String image);




}
