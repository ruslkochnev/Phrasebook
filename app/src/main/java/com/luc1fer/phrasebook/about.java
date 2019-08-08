package com.luc1fer.phrasebook;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.google.android.gms.ads.*;
public class about extends AppCompatActivity {
    String title;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        title = getIntent().getExtras().getString("title");
        Toolbar toolbar4 = findViewById(R.id.toolbar4);
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
/*
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("4DEDDDCDB26C87FB2579CAE68F90D18A").build();

        mAdView.loadAd(adRequest);

*/

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
