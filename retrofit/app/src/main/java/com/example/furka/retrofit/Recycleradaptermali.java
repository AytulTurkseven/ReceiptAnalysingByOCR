package com.example.furka.retrofit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import okhttp3.HttpUrl;

/**
 * Created by csa on 01-May-17.
 */

public class Recycleradaptermali extends RecyclerView.Adapter<Recycleradaptermali.MyHolder>{

    private List<mali> list;
    private Context context;
    //ImageLoader imageLoader;


    HttpUrl a;






    public Recycleradaptermali(List<mali> list, Context context) {
        this.list = list;
        this.context=context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rapor,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        int test=Integer.valueOf(list.get(position).getId());
        int mutfak=1,temizlik=2, giyim=3, kozmetik=4,alkol=5,sigara=6;




            if (test == mutfak)
                holder.tur.setText("Mutfak masrafı");
            if (test == temizlik)
                holder.tur.setText("Temizlik masrafı");
            if (test == giyim)
                holder.tur.setText("Giyim masrafı");
            if (test == kozmetik)
                holder.tur.setText("Kozmetik masrafı");
            if (test == alkol)
                holder.tur.setText("Alkol masrafı");
            if (test == sigara)
                holder.tur.setText("sigara masrafı");


       // if(!list.get(position).getFiyat().equals("0"))
            holder.fiyat.setText(list.get(position).getFiyat());


    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

     //   ImageView image;
        TextView tur,fiyat;

        public MyHolder(View itemView) {
            super(itemView);
          //  image= (ImageView) itemView.findViewById(R.id.card);

            tur = (TextView) itemView.findViewById(R.id.textView);
            fiyat = (TextView) itemView.findViewById(R.id.textView2);


        }


    }


/*
    public void addImages(List<Images> resim){

        for (Images im: resim){
            list.add(im);
        }
    }*/

}



