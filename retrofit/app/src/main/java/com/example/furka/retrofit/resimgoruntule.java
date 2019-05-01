package com.example.furka.retrofit;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class resimgoruntule extends AppCompatActivity {

    RecyclerView recyclerView;
  //  List<ImageClass> listing;
    GridLayoutManager layoutManager;
    Recycleradapter recyclerAdapter;
    Toolbar toolbar;
   // Recycleradapter recyclerAdapter2;
    String kullaniciadi;
    TextView tar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resimgoruntule);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Resim Galeri Seç");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }



        Bundle gelenVeri=getIntent().getExtras();
        String tarihal =gelenVeri.getCharSequence("anahtar").toString();

        recyclerView = (RecyclerView) findViewById(R.id.resimana);
        layoutManager=new GridLayoutManager(this,3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
       //ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(this).build();
       // ImageLoader.getInstance().init(config);
        ApiInterface apiInterface = ApiClient.getApiclient().create(ApiInterface.class);
        kullaniciadi=AcilisActivity.prefConfig.kullaniciadkontrol();
        Call<List<ImageRecyclerClass>> call = apiInterface.rcek("aa",tarihal);
        // Call<List<ImageClass>> call= apiInterface.resimcek("a");
        call.enqueue(new Callback<List<ImageRecyclerClass>>() {
            @Override
            public void onResponse(Call<List<ImageRecyclerClass>> call, Response<List<ImageRecyclerClass>> response) {

                List<Images> list = response.body().get(1).getImages();
                recyclerAdapter = new Recycleradapter(list,resimgoruntule.this);
                recyclerView.setAdapter(recyclerAdapter);

                Toast.makeText(resimgoruntule.this,"Başarılı",Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onFailure(Call<List<ImageRecyclerClass>> call, Throwable t) {

                Toast.makeText(resimgoruntule.this,"Veri bulunamadı yada servise bağlanılamadı",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(resimgoruntule.this, resimacilis.class);
                startActivity(intent);
            }
        });



    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(resimgoruntule.this, resimacilis.class);
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


    /*public void itemClicked(View view, int position) {
        if(position==2) {
            Intent intent = new Intent(resimgoruntule.this, resim.class);
            intent.putExtra("ItemPosition", position);
            startActivity(intent);
        }
        else if (position ==4){
            Intent intent = new Intent(resimgoruntule.this, resim.class);
            intent.putExtra("ItemPosition", position);
            startActivity(intent);
        }
        else if (position==1){
            Intent intent = new Intent(resimgoruntule.this, resim.class);
            intent.putExtra("ItemPosition", position);
            startActivity(intent);
        }
        else {
            System.out.println("position...."+position);
        }
    }*/

}
