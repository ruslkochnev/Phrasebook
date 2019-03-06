package com.luc1fer.phrasebook;


import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
public class inflate extends AppCompatActivity {
    private PhraseDBHelper mDBhelper;
    private SQLiteDatabase mDb;
    int i;
    int j;
    String title;
    private AdView mAdView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        i = getIntent().getExtras().getInt("i");
        j = getIntent().getExtras().getInt("j");
        title = getIntent().getExtras().getString("title");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("4DEDDDCDB26C87FB2579CAE68F90D18A").build();

        mAdView.loadAd(adRequest);


        mDBhelper = new PhraseDBHelper(this);
        try {
            mDBhelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        try {
            mDb = mDBhelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        ArrayList<HashMap<String, Object>> commons = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> common;
        Cursor cursor = mDb.rawQuery("SELECT * FROM phrase WHERE _id BETWEEN " + i +" AND " + j +";", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            common = new HashMap<String, Object>();
            common.put("russian",  cursor.getString(1));
            common.put("english",  cursor.getString(2));
            commons.add(common);
            cursor.moveToNext();
        }
        cursor.close();
        String[] from = { "russian", "english"};
        int[] to = {R.id.textViewRussian, R.id.textViewEnglish};
        SimpleAdapter adapter = new SimpleAdapter(this, commons, R.layout.adapter_item, from, to);
        ListView listView = findViewById(R.id.listViewItem);
        listView.setAdapter(adapter);

        Toolbar toolbar2 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar2.setTitle(title);
        toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflate.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
