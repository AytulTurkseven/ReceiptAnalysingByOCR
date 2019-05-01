package com.example.furka.retrofit;

import com.google.gson.annotations.SerializedName;

public class ekle {

    @SerializedName("urun")
    private String Urun;

    @SerializedName("response")
    private String Response;

    public String getResponse() {
        return Response;
    }



    public String getUrun() {
        return Urun;
    }
}
