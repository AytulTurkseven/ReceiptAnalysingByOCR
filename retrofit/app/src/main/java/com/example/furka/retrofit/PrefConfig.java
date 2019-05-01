package com.example.furka.retrofit;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class PrefConfig {

    private SharedPreferences sharedPreferences;
    private Context context;

    public PrefConfig(Context context){


        this.context=context;
        sharedPreferences=context.getSharedPreferences(context.getString(R.string.pref_file),Context.MODE_PRIVATE);


    }

    public void girisdurumu(boolean durum){

        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status),durum);
        editor.commit();

    }

    public boolean girisdurumukontrol(){

        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status),false);
    }

    public void ad(String name ){

        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_kullaniciadi),name);
        editor.commit();


    }

    public String adkontrol(){

        return sharedPreferences.getString(context.getString(R.string.pref_kullaniciadi),"User");
    }

    public void kullaniciad(String kullaniciadi ){

        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_kullaniciadi1),kullaniciadi);
        editor.commit();


    }

    public String kullaniciadkontrol(){

        return sharedPreferences.getString(context.getString(R.string.pref_kullaniciadi1),"User1");
    }

    public void yaz(String mesaj){
        Toast.makeText(context,mesaj,Toast.LENGTH_SHORT).show();

    }




}
