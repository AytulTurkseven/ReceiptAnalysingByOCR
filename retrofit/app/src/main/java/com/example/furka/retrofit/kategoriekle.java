package com.example.furka.retrofit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class kategoriekle extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner kategori;
    TextView urunad;
    Button ekle;
    public static String al="al";
    String tarihal;
    String urunal;
    int kategoriid = 0;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategoriekle);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Kategori Ekle");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        Bundle gelenVeri=getIntent().getExtras();
        urunal = gelenVeri.getCharSequence("anahtar1").toString();

        kategori=(Spinner) findViewById(R.id.urunadspinner);
        ekle=(Button) findViewById(R.id.ekle);
        urunad=(TextView) findViewById(R.id.urunad);
        urunad.setText(urunal);

        ArrayAdapter kategorilist=ArrayAdapter.createFromResource(this,R.array.kategori,android.R.layout.simple_spinner_item);
        kategori.setAdapter(kategorilist);
        kategori.setOnItemSelectedListener(this);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(kategoriekle.this, raporacilis.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.empty_menu, menu);
        return true;
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        final TextView tarihtext=(TextView) view;
        // tarihal= (String)tarihtext.getText();


        ekle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_UP:{


                        CharSequence secilenkategori=(String)tarihtext.getText();

                        if (secilenkategori.equals("Mutfak")){
                            kategoriid=1;
                        }
                        if (secilenkategori.equals("Temizlik")){
                            kategoriid=2;
                        }
                        if (secilenkategori.equals("Giyim")){
                            kategoriid=3;
                        }
                        if (secilenkategori.equals("Kozmetik")){
                            kategoriid=4;
                        }
                        if (secilenkategori.equals("Alkol")){
                            kategoriid=5;
                        }
                        if (secilenkategori.equals("Sigara")){
                            kategoriid=6;
                        }




                        Call<ekle> callolmayan = AcilisActivity.apiInterface.urunekle(urunal, String.valueOf(kategoriid));
                        callolmayan.enqueue(new Callback<ekle>() {
                            @Override
                            public void onResponse(Call<ekle> call, Response<ekle> response) {
                                // String list = response.body().get(0).getOlmayanlar().get(0).getResponse();
//                        if (response.body().get(0).getOlmayanlar().get(0).getResponse().equals("basarili")){
//                            Toast.makeText(kategoriekle.this,"ggooooooo",Toast.LENGTH_SHORT).show();
//                        }

                                Intent ıntent=new Intent(kategoriekle.this,raporacilis.class);
                                startActivity(ıntent);

                                Toast.makeText(kategoriekle.this,"Başarı ile ürün eklenmiştir",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<ekle> call, Throwable t) {
                                Toast.makeText(kategoriekle.this,"ürün eklenememiştir",Toast.LENGTH_SHORT).show();

                            }
                        });



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



    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
