package com.example.furka.retrofit;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class kayit extends Fragment {


    private EditText Ad,Soyad,Kullaniciadi,Sifre;
    private Button kaydol;

    public kayit() {
        // Required empty public constructor
    }



    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
   View view= inflater.inflate(R.layout.fragment_kayit, container, false);
        Ad=view.findViewById(R.id.ad);
        Soyad=view.findViewById(R.id.soyad);
        Kullaniciadi=view.findViewById(R.id.kullaniciadi);
        Sifre=view.findViewById(R.id.sifre);
        kaydol=view.findViewById(R.id.buttonkayit);

        kaydol.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_UP:{

                       kayitfonk();
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


        return view;

    }


    public void kayitfonk() {
        String ad = Ad.getText().toString();
        String soyad = Soyad.getText().toString();
        String kullaniciadi = Kullaniciadi.getText().toString();
        String sifre = Sifre.getText().toString();

        Call<kullanici> call = AcilisActivity.apiInterface.kaydol(ad,soyad,kullaniciadi,sifre);
        call.enqueue(new Callback<kullanici>() {
            @Override
            public void onResponse(Call<kullanici> call, Response<kullanici> response) {
                if (response.body().getResponse().equals("basarili")){
                    AcilisActivity.prefConfig.yaz("kayıt başarılı");
                }
                else if(response.body().getResponse().equals("kullanici adi kullaniliyor")){
                    AcilisActivity.prefConfig.yaz("Kullanıcı adı kullanılıyor");

                }
                else if(response.body().getResponse().equals("hata")){
                    AcilisActivity.prefConfig.yaz("hata");

                }

            }

            @Override
            public void onFailure(Call<kullanici> call, Throwable t) {
                AcilisActivity.prefConfig.yaz("islem basarisiz");

            }
        });

        Ad.setText("");
        Soyad.setText("");
        Kullaniciadi.setText("");
        Sifre.setText("");

    }






}
