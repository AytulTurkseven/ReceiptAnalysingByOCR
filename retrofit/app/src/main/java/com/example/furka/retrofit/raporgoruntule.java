package com.example.furka.retrofit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class raporgoruntule extends AppCompatActivity {

    RecyclerView recyclerView;
    //  List<ImageClass> listing;
    GridLayoutManager layoutManager;
    Recycleradaptermali recyclerAdaptermali;
    // Recycleradapter recyclerAdapter2;
    String kullaniciadi;
    ProgressBar pb;


    private Bundle extras = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raporgoruntule);

         Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Raporum");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        pb = findViewById(R.id.progress_bar);

        pb.setVisibility(View.VISIBLE);
        Bundle gelenVeri = getIntent().getExtras();
        final String tarihal = gelenVeri.getCharSequence("anahtar").toString();
        kullaniciadi = AcilisActivity.prefConfig.kullaniciadkontrol();

        Call<List<maliyetclass>> callolmayan = AcilisActivity.apiInterface.raporolmayan(kullaniciadi, tarihal);
        callolmayan.enqueue(new Callback<List<maliyetclass>>() {
            @Override
            public void onResponse(Call<List<maliyetclass>> call, Response<List<maliyetclass>> response) {
                pb.setVisibility(View.GONE);
                List<olmayan> list = response.body().get(0).getOlmayanlar();
                String a = list.get(0).getUrun();
                CharSequence gönderilenYazi = (String) a;
                Intent ıntent = new Intent(raporgoruntule.this, kategoriekle.class);
                ıntent.putExtra("anahtar1", gönderilenYazi);
                startActivity(ıntent);


                //  Toast.makeText(raporgoruntule.this,a,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<List<maliyetclass>> callolmayan, Throwable t) {
                // Toast.makeText(raporgoruntule.this,"raporgoruntule gg",Toast.LENGTH_LONG).show();
                ApiInterface apiInterface = ApiClient.getApiclient().create(ApiInterface.class);

                Call<List<maliyetclass>> call = apiInterface.rapor(kullaniciadi, tarihal);
                // Call<List<ImageClass>> call= apiInterface.resimcek("a");
                call.enqueue(new Callback<List<maliyetclass>>() {
                    @Override
                    public void onResponse(Call<List<maliyetclass>> call, Response<List<maliyetclass>> response) {
                        pb.setVisibility(View.GONE);
                        List<mali> listmali = response.body().get(1).getMaliyet();

                        recyclerAdaptermali = new Recycleradaptermali(listmali, raporgoruntule.this);
                        recyclerView.setAdapter(recyclerAdaptermali);
                     //   Toast.makeText(raporgoruntule.this, "Fiş raporunuz", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onFailure(Call<List<maliyetclass>> call, Throwable t) {
                        Toast.makeText(raporgoruntule.this, "Veri bulunamadı yada servise bağlanılamadı", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(raporgoruntule.this, raporacilis.class);
                        startActivity(intent);
                    }
                });

            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.raporana);
        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        //ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(this).build();
        // ImageLoader.getInstance().init(config);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(raporgoruntule.this, raporacilis.class);
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

}
