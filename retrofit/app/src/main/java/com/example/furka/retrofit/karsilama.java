package com.example.furka.retrofit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;




public class karsilama extends Fragment {
    private TextView karsilama;
    private Button cikis,goruntule,rapor,yukle;

    Cikiskontrol cikiskontrol;
    Intent intent;

    public interface Cikiskontrol{

        public void cikis();
    }



    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_karsilama, container, false);

        karsilama=view.findViewById(R.id.girismetni);
        karsilama.setText("Hoşgeldin "+AcilisActivity.prefConfig.adkontrol());
        yukle=view.findViewById(R.id.yukle);
        cikis=view.findViewById(R.id.cikis);
        goruntule=view.findViewById(R.id.goruntule);
        rapor=view.findViewById(R.id.rapor);


        cikis.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_UP:{

                     cikiskontrol.cikis();
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

        yukle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_UP:{

                        intent = new Intent(karsilama.getContext(), ResimyukleActivity.class);
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
        goruntule.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_UP:{

                        intent = new Intent(goruntule.getContext(), resimacilis.class);
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

        rapor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_UP:{

                        intent = new Intent(rapor.getContext(), raporacilis.class);
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
        return view;

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;
        cikiskontrol = (Cikiskontrol) activity;
    }


}
