package com.example.furka.retrofit;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


public class AcilisActivity extends AppCompatActivity implements  giris.GirisKontrolu,karsilama.Cikiskontrol  {


    public static PrefConfig prefConfig;
    public static ApiInterface apiInterface;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acilis);

        prefConfig=new PrefConfig(this);
        apiInterface = ApiClient.getApiclient().create(ApiInterface.class);


            toolbar= findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("OCR Alışveriş Raporu");
          //  getSupportActionBar().setIcon(R.drawable.logo);



        if (findViewById(R.id.fragment_container)!=null){


            if (savedInstanceState!=null){
                return;
            }

            if (prefConfig.girisdurumukontrol()){

                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new karsilama()).commit();
            }
            else{
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new giris()).commit();
            }
        }
    }


    @Override
    public void kaydol() {
    getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container,new kayit()).addToBackStack(null).commit();
    }

    @Override
    public void giris(String ad,String kullaniciadi) {
        prefConfig.ad(ad);
        prefConfig.kullaniciad(kullaniciadi);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,new karsilama()).addToBackStack(null).commit();

    }


    @Override
    public void cikis() {
        prefConfig.girisdurumu(false);
        prefConfig.ad("Kullanici");
        prefConfig.kullaniciad("Kullanici");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,new giris()).addToBackStack(null).commit();
    }
}
