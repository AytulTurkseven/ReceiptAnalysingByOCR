package com.example.furka.retrofit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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

public class resimacilis extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner tarih;
    Button resim;
    Toolbar toolbar;
  //   public static String al="al";
    String tarihal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resimacilis);

        tarih=(Spinner) findViewById(R.id.tarih);
        resim=(Button) findViewById(R.id.resim);

        ArrayAdapter tarihlist=ArrayAdapter.createFromResource(this,R.array.tarih,android.R.layout.simple_spinner_item);
        tarih.setAdapter(tarihlist);
        tarih.setOnItemSelectedListener(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Resim Seç");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(resimacilis.this, AcilisActivity.class);
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
        final TextView tarihtext = (TextView) view;
        // tarihal= (String)tarihtext.getText();



        resim.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP: {

                        CharSequence gönderilenYazi = (String) tarihtext.getText();
                        ;
                        Intent ıntent = new Intent(resimacilis.this, resimgoruntule.class);///İntent ouşturup 2. activity'e gideceğini belirledik.
                        ıntent.putExtra("anahtar", gönderilenYazi);//Gönderilecek veriyi ve bir anahtar belirledik.
                        startActivity(ıntent);
                        Button view = (Button) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();

                        break;
                    }
                    case MotionEvent.ACTION_DOWN: {
                        Button view = (Button) v;
                        view.getBackground().setColorFilter(0x77700000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();

                        break;
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        Button view = (Button) v;
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
