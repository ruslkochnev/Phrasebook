package com.luc1fer.phrasebook;


import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.IOException;

public class search extends AppCompatActivity {
    private PhraseDBHelper mDBhelper;
    private SQLiteDatabase mDb;

    String title;
    private AdView mAdView;



    ListView userList;
    EditText userFilter;

    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        title = getIntent().getExtras().getString("title");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("4DEDDDCDB26C87FB2579CAE68F90D18A").build();
        /*AdRequest adRequest = new AdRequest.Builder().build();*/
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

        userList = findViewById(R.id.userList);
        userFilter = findViewById(R.id.userFilter);

        try {
            mDb = mDBhelper.getReadableDatabase();
            userCursor = mDb.rawQuery("select * from " + PhraseDBHelper.TABLE_NAME, null);
            String[] headers = new String[]{PhraseDBHelper.RUSSIAN, PhraseDBHelper.ENGLISH};
            userAdapter = new SimpleCursorAdapter(this, R.layout.adapter_item,
                    userCursor, headers, new int[]{R.id.textViewRussian, R.id.textViewEnglish}, 0);

            // если в текстовом поле есть текст, выполняем фильтрацию
            // данная проверка нужна при переходе от одной ориентации экрана к другой
            if(!userFilter.getText().toString().isEmpty())
                userAdapter.getFilter().filter(userFilter.getText().toString());

            // установка слушателя изменения текста
            userFilter.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) { }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                // при изменении текста выполняем фильтрацию
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    userAdapter.getFilter().filter(s.toString());
                }
            });

            // устанавливаем провайдер фильтрации
            userAdapter.setFilterQueryProvider(new FilterQueryProvider() {
                @Override
                public Cursor runQuery(CharSequence constraint) {

                    if (constraint == null || constraint.length() == 0) {

                        return mDb.rawQuery("select * from " + PhraseDBHelper.TABLE_NAME, null);
                    }
                    else {
                        return mDb.rawQuery("select * from " + PhraseDBHelper.TABLE_NAME + " where " +
                                        PhraseDBHelper.RUSSIAN + " like ?" + " or " + PhraseDBHelper.ENGLISH + " like ?",
                                new String[]{"%" + constraint.toString() + "%", "%" + constraint.toString() + "%"});
                    }
                }
            });

            userList.setAdapter(userAdapter);
        }
        catch (SQLException ex){}


        Toolbar toolbar3 = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar3);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar3.setTitle(title);
        toolbar3.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(search.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
