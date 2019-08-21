package com.luc1fer.phrasebook;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class about extends AppCompatActivity {
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        title = getIntent().getExtras().getString("title");
        Toolbar toolbar4 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar4);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar4.setTitle(title);
        toolbar4.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(about.this, MainActivity.class);
                startActivity(intent3);
            }
        });
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
    public void onImageButtonClick(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/sparkysparkybooman"));
        startActivity(browserIntent);
    }
    public void onLinkClick(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Отклик из приложения");
        intent.putExtra(Intent.EXTRA_TEXT, "Добрый день!");
        intent.setData(Uri.parse("mailto:ruslkochnev@gmail.com")); // or just "mailto:" for blank
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
        startActivity(intent);
    }
}
