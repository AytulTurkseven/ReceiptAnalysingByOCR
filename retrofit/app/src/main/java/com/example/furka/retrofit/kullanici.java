package com.example.furka.retrofit;

import com.google.gson.annotations.SerializedName;

public class kullanici {


    @SerializedName("response")
    private String Response;

    @SerializedName("name")
    private String Name;

    @SerializedName("kullaniciadi")
    private String Kullaniciadi;


    public String getResponse() {
        return Response;
    }

    public String getName() {
        return Name;
    }
    public String getKullaniciadi() {
        return Kullaniciadi;
    }
}
