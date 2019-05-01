package com.example.furka.retrofit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.HttpUrl;
import retrofit2.Callback;


public class Recycleradapter extends RecyclerView.Adapter<Recycleradapter.MyHolder>{
    private static final String TAG = "Bitirme";
    private List<Images> list;
    private Context context;
    ImageLoader imageLoader;


    HttpUrl a;






    public Recycleradapter(List<Images> list, Context context) {
        this.list = list;
        this.context=context;
    }



    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resimler,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Images res =list.get(position);
        a=ApiClient.getApiclient().baseUrl();
        //Glide.with(context).load("http://192.168.1.35/bitirme/uploads/"+res.getImage()).into(holder.image);

            Glide.with(context).load(a+"/uploads/"+res.getImage()).into(holder.image);

       final String image1 =res.getImage();
        //imageLoader.displayImage("https://indigodergisi.com/wp-content/uploads/2017/05/1494118823_EpilepsiveBen_6_11Ya_____kincisi__Berat___pek-265x198.jpg", holder.image);
        // String abc=res.getImage();
      //  Picasso.get().load("https://indigodergisi.com/wp-content/uploads/2017/05/1494118823_EpilepsiveBen_6_11Ya_____kincisi__Berat___pek-265x198.jpg").into(holder.image);

        holder.layoutresim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Intent intent = new Intent(context, resim.class);

                intent.putExtra("image_url", image1);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        ImageView image;
        LinearLayout layoutresim;

        public MyHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.card);
            layoutresim= (LinearLayout) itemView.findViewById(R.id.resimlayout);





        }


    }


/*
    public void addImages(List<Images> resim){

        for (Images im: resim){
            list.add(im);
        }
    }*/

}



