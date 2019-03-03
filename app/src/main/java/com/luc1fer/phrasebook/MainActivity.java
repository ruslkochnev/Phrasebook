package com.luc1fer.phrasebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;



public class MainActivity extends AppCompatActivity {
    private AdView mAdView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        Button button = findViewById(R.id.buttonTrip);
    }

    public void onTripButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this, inflate.class);
        intent.putExtra("i", 2);
        intent.putExtra("j", 95);
        intent.putExtra("title", "Путешествия");
        startActivity(intent);
    }

/*
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonTrip:
                Intent intentTrip = new Intent(MainActivity.this, inflate.class);
                startActivity(intentTrip);
                break;
            case R.id.buttonTransport:
                Intent intentTransport = new Intent(MainActivity.this, inflate.class);
                startActivity(intentTransport);
                break;
            case R.id.buttonHotel:
                Intent intentHotel = new Intent(MainActivity.this, inflate.class);
                startActivity(intentHotel);
                break;
            case R.id.buttonCafe:
                Intent intentCafe = new Intent(MainActivity.this, inflate.class);
                startActivity(intentCafe);
                break;
            case R.id.buttonExcursion:
                Intent intentExcursion = new Intent(MainActivity.this, inflate.class);
                startActivity(intentExcursion);
                break;
            case R.id.buttonBuy:
                Intent intentBuy = new Intent(MainActivity.this, inflate.class);
                startActivity(intentBuy);
                break;
            case R.id.buttonRadio:
                Intent intentRadio = new Intent(MainActivity.this, inflate.class);
                startActivity(intentRadio);
                break;
            case R.id.buttonBank:
                Intent intentBank = new Intent(MainActivity.this, inflate.class);
                startActivity(intentBank);
                break;
            case R.id.buttonHealth:
                Intent intentHealth = new Intent(MainActivity.this, inflate.class);
                startActivity(intentHealth);
                break;
            case R.id.buttonCommon:
                Intent intentCommon = new Intent(MainActivity.this, inflate.class);
                startActivity(intentCommon);
                break;*/
}
