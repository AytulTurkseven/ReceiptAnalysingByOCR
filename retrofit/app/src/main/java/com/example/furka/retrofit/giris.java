package com.example.furka.retrofit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class giris extends Fragment {


    TextView uyeol;
    EditText Kullaniciadi, Sifre;
    Button giris;
    GirisKontrolu girisKontrolu;


    public interface GirisKontrolu {

        public void kaydol();

        public void giris(String ad,String kullaniciadi);

    }


    public giris() {
        // Required empty public constructor
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_giris, container, false);
        uyeol = view.findViewById(R.id.uyeol);
        Kullaniciadi = view.findViewById(R.id.kullaniciadi);
        Sifre = view.findViewById(R.id.sifre);
        giris = view.findViewById(R.id.giris);


        giris.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_UP:{

                        girisfonk();
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

        uyeol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girisKontrolu.kaydol();

            }
        });
        return view;


    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        Activity activity = (Activity) context;
        girisKontrolu = (GirisKontrolu) activity;


    }

    private void girisfonk() {

        String kullaniciadi = Kullaniciadi.getText().toString();
        String sifre = Sifre.getText().toString();
        Call<kullanici> call = AcilisActivity.apiInterface.girisyap(kullaniciadi, sifre);
        call.enqueue(new Callback<kullanici>() {
            @Override
            public void onResponse(Call<kullanici> call, Response<kullanici> response) {
                if (response.body().getResponse().equals("basarili")) {
                    AcilisActivity.prefConfig.girisdurumu(true);
                    girisKontrolu.giris(response.body().getName(),response.body().getKullaniciadi());

                } else if (response.body().getResponse().equals("hatali")) {
                    AcilisActivity.prefConfig.yaz("Tekrar Dene");

                }

            }

            @Override
            public void onFailure(Call<kullanici> call, Throwable t) {
                AcilisActivity.prefConfig.yaz("islem basarisiz");

            }


        });

        Kullaniciadi.setText("");
        Sifre.setText("");

    }
}
