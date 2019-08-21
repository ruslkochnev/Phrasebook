package com.luc1fer.phrasebook;

import android.Manifest;
import android.content.Intent;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;



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

       /* mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("4DEDDDCDB26C87FB2579CAE68F90D18A").build();

        mAdView.loadAd(adRequest);
*/

            // получаем наши `SharedPreferences`
            PreferencesManager prefManager = new PreferencesManager(this);
        // проверяем нашу запись в файле настроек. Если реклама не отключена, то
        // у нас будет true записано, то есть состояние ВКЛЮЧЕНО
        // а также проверяем подключение к сети Internet простеньким способом
        // true - enabled  | false - disabled
        boolean adsState = prefManager.getAdsStatus();
        if (adsState && CheckURLConnection.isNetworkAvailable(this)) {
            Ads.showBanner(this, true);
        } else {
            Ads.showBanner(this, false);

        }
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonTrip:
                Intent intentTrip = new Intent(MainActivity.this, inflate.class);
                intentTrip.putExtra("i", 2);
                intentTrip.putExtra("j", 95);
                intentTrip.putExtra("title", getString(R.string.trip));
                startActivity(intentTrip);
                break;
            case R.id.buttonTransport:
                Intent intentTransport = new Intent(MainActivity.this, inflate.class);
                intentTransport.putExtra("i", 97);
                intentTransport.putExtra("j", 215);
                intentTransport.putExtra("title", getString(R.string.transport));
                startActivity(intentTransport);
                break;
            case R.id.buttonHotel:
                Intent intentHotel = new Intent(MainActivity.this, inflate.class);
                intentHotel.putExtra("i", 217);
                intentHotel.putExtra("j", 324);
                intentHotel.putExtra("title", getString(R.string.hotel));
                startActivity(intentHotel);
                break;
            case R.id.buttonCafe:
                Intent intentCafe = new Intent(MainActivity.this, inflate.class);
                intentCafe.putExtra("i", 326);
                intentCafe.putExtra("j", 417);
                intentCafe.putExtra("title", getString(R.string.cafe));
                startActivity(intentCafe);
                break;
            case R.id.buttonExcursion:
                Intent intentExcursion = new Intent(MainActivity.this, inflate.class);
                intentExcursion.putExtra("i", 419);
                intentExcursion.putExtra("j", 502);
                intentExcursion.putExtra("title", getString(R.string.excursion));
                startActivity(intentExcursion);
                break;
            case R.id.buttonBuy:
                Intent intentBuy = new Intent(MainActivity.this, inflate.class);
                intentBuy.putExtra("i", 504);
                intentBuy.putExtra("j", 627);
                intentBuy.putExtra("title", getString(R.string.buy));
                startActivity(intentBuy);
                break;
            case R.id.buttonRadio:
                Intent intentRadio = new Intent(MainActivity.this, inflate.class);
                intentRadio.putExtra("i", 629);
                intentRadio.putExtra("j", 679);
                intentRadio.putExtra("title", getString(R.string.radio));
                startActivity(intentRadio);
                break;
            case R.id.buttonBank:
                Intent intentBank = new Intent(MainActivity.this, inflate.class);
                intentBank.putExtra("i", 681);
                intentBank.putExtra("j", 707);
                intentBank.putExtra("title", getString(R.string.bank));
                startActivity(intentBank);
                break;
            case R.id.buttonHealth:
                Intent intentHealth = new Intent(MainActivity.this, inflate.class);
                intentHealth.putExtra("i", 709);
                intentHealth.putExtra("j", 780);
                intentHealth.putExtra("title", getString(R.string.health));
                startActivity(intentHealth);
                break;
            case R.id.buttonCommon:
                Intent intentCommon = new Intent(MainActivity.this, inflate.class);
                intentCommon.putExtra("i", 782);
                intentCommon.putExtra("j", 953);
                intentCommon.putExtra("title", getString(R.string.common));
                startActivity(intentCommon);
                break;

        }
    }

    public void onClickSearchMenuButton(MenuItem item) {
        Intent intentSearch = new Intent(MainActivity.this, search.class);
        intentSearch.putExtra("title", getString(R.string.search_bar));
        startActivity(intentSearch);
    }

    public void onClickMenuButton(MenuItem item) {
        Intent intentAbout = new Intent(MainActivity.this, about.class);
        intentAbout.putExtra("title", getString(R.string.about_hint));
        startActivity(intentAbout);
    }


}