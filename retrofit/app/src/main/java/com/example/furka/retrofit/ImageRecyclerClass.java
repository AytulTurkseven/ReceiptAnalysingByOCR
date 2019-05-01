package com.example.furka.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageRecyclerClass {

    @SerializedName("status")
    private String Status;

    @SerializedName("images")
    private List<Images> Images;

    public String getStatus() {
        return Status;
    }

    public List<Images> getImages() {
        return Images;
    }
}

class Images{


    @SerializedName("id")
    private String Id;


    @SerializedName("image")
    private String Image;


    public String getId() {
        return Id;
    }

    public String getImage() {
        return Image;
    }

}
