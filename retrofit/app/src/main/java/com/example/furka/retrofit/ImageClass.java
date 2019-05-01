package com.example.furka.retrofit;

import com.google.gson.annotations.SerializedName;

public class ImageClass {

    @SerializedName("image")
    private String Image;

    @SerializedName("kullaniciadi")
    private String Kullaniciadi;

    @SerializedName("response")
    private String Response;



    public String getImage() {
        return Image;
    }

    public String getKullaniciadi() {
        return Kullaniciadi;
    }
    public String getResponse() {
        return Response;
    }


    public void setImage(String image) {
        Image = image;
    }

    public void setKullaniciadi(String kullaniciadi) {
        Kullaniciadi = kullaniciadi;
    }

    public void setResponse(String response) {
        Response = response;
    }
}
