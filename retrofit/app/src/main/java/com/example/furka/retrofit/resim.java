package com.example.furka.retrofit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class resim extends AppCompatActivity {

    private ImageView resim;
    Button geri,sil;
    Context context;
    Intent intent;
    String imageUrl;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resim);

        resim = (ImageView) findViewById(R.id.resim);
        geri = (Button) findViewById(R.id.geri);
        sil = (Button) findViewById(R.id.sil);

        geri.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_UP:{

                        intent = new Intent(com.example.furka.retrofit.resim.this, resimacilis.class);
                        startActivity(intent);
                        Button view=(Button)v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();

                        break;
                    }
                    case MotionEvent.ACTION_DOWN:{
                        Button view=(Button)v;
                        view.getBackground().setColorFilter(0x77700000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();

                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:{
                        Button view=(Button)v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();

                        break;
                    }


                }



                return true;
            }


        });

        if(getIntent().hasExtra("image_url")){

             imageUrl = getIntent().getStringExtra("image_url");


            Glide.with(this)
                    .asBitmap()
                    .load(ApiClient.getApiclient().baseUrl()+"uploads/"+imageUrl)
                    .into(resim);
        }



//        sil.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent motionEvent) {
//                switch(motionEvent.getAction()){
//                    case MotionEvent.ACTION_UP:{
//
//
//
//                        Call<ImageClass> callsil = AcilisActivity.apiInterface.resimsil(imageUrl);
//                        callsil.enqueue(new Callback<ImageClass>() {
//                            @Override
//                            public void onResponse(Call<ImageClass> call, Response<ImageClass> response) {
//                                // String list = response.body().get(0).getOlmayanlar().get(0).getResponse();
////                        if (response.body().get(0).getOlmayanlar().get(0).getResponse().equals("basarili")){
////                            Toast.makeText(kategoriekle.this,"ggooooooo",Toast.LENGTH_SHORT).show();
////                        }
//
//
//                            }
//
//                            @Override
//                            public void onFailure(Call<ImageClass> call, Throwable t) {
//                                Toast.makeText(resim.this,"Silme Başarılı",Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
//
//
//                        Button view=(Button)v;
//                        view.getBackground().clearColorFilter();
//                        view.invalidate();
//
//                        break;
//                    }
//                    case MotionEvent.ACTION_DOWN:{
//                        Button view=(Button)v;
//                        view.getBackground().setColorFilter(0x77700000, PorterDuff.Mode.SRC_ATOP);
//                        view.invalidate();
//
//                        break;
//                    }
//                    case MotionEvent.ACTION_CANCEL:{
//                        Button view=(Button)v;
//                        view.getBackground().clearColorFilter();
//                        view.invalidate();
//
//                        break;
//                    }
//
//
//                }
//
//
//
//                return true;
//            }
//
//
//        });

        }
     //   Picasso.get().load(ApiClient.getApiclient().baseUrl() + "uploads/63.jpg").into(resim);

    }

