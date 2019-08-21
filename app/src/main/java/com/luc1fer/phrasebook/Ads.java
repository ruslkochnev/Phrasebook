package com.luc1fer.phrasebook;

import android.app.Activity;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
/*
public class Ads {
    // создаем метод для создания баннера
    public static void showBanner(final Activity activity) {

        // создаем баннер, находим его по id
        final AdView banner = (AdView) activity.findViewById(R.id.adView);
        // строит и загружает баннер
        // импорт android.gms.ads
        AdRequest adRequest = new AdRequest.Builder().build();
        banner.loadAd(adRequest);

        // слушатель загрузки баннера
        banner.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                setupContentViewPadding(activity, banner.getHeight());
            }
        });
    }

    // метод подвигает нижний край экрана, на высоте размера баннера
    public static void setupContentViewPadding(Activity activity, int padding) {
        View view = activity.findViewById(R.id.coordinator);
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), padding);
    }
}
*/


public class Ads {
    public static void showBanner(final Activity activity, boolean adsState) {

        // вместо LinearLayout у Вас будет ВАШ View, который располагается над AdView. В вопросе фигурирует CoordinatorLayout, вот его и нужно будет здесь инициализировать по аналогии с LinearLayout
        final CoordinatorLayout adsContainer = (CoordinatorLayout) activity.findViewById(R.id.container);
        final AdView mAdView = (AdView) activity.findViewById(R.id.adView);

        if (adsState) {
            AdRequest adRequest;
            // DEBUG or RELEASE
            if (BuildConfig.DEBUG) {
                adRequest = new AdRequest.Builder()
                        .addTestDevice("9D92604DD370B04EE4DFD67486D0CE56")
                        .build();
            } else {
                adRequest = new AdRequest.Builder()
                        .build();
            }
            mAdView.loadAd(adRequest);

            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    setupContentViewPadding(activity, mAdView.getHeight());
                }
            });
        } else {
            adsContainer.removeView(mAdView);

        }
    }

    public static void setupContentViewPadding(Activity activity, int padding) {
        View view = activity.findViewById(R.id.container);
        // рекомендую добавить 8dp отступа над баннером, чтобы была ЧЕТКАЯ граница, которая отделяет его и пользовательские данные (Google просто рекомендует делать границу, иначе могут быть ложные клики и бан AdMob аккаунта)
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), padding + 8);
    }
}
