package com.example.furka.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class maliyetclass {

    @SerializedName("olmayanlar")
    private List<olmayan> Olmayan;

    @SerializedName("maliyet")
    private List<mali> Maliyet;

    public List<olmayan> getOlmayanlar() {
        return Olmayan;
    }

    public List<mali> getMaliyet() {
        return Maliyet;
    }
}

class mali{


    @SerializedName("id")
    private String Id;

    @SerializedName("fiyat")
    private String Fiyat;

    public String getId() {
        return Id;
    }

    public String getFiyat() {
        return Fiyat;
    }





}


class olmayan{


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
