package com.luc1fer.phrasebook;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {

    private static SharedPreferences mSPref;
    private SharedPreferences.Editor mSPEditor;

    private static final String APP_PREF    = "preferences";// имя файла настроек Вашего приложения

    private static final String APP_ADS_STATUS = "adsStatus";  // статус рекламы

    public PreferencesManager(Context context) {
        mSPref = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE);
    }

    public void setAdsStatus(boolean adsStatus) {
        // true - enabled  | false - disabled
        mSPEditor = mSPref.edit();
        mSPEditor.putBoolean(APP_ADS_STATUS, adsStatus);
        mSPEditor.apply();
    }

    public boolean getAdsStatus() {
        return mSPref.getBoolean(APP_ADS_STATUS, true);
    }
}