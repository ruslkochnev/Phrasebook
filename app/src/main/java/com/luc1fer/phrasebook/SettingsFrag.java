package com.luc1fer.phrasebook;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;


import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;

public class SettingsFrag extends Fragment implements BillingProcessor.IBillingHandler {

    private boolean adsStatus;    // храним текущий статус отображения рекламы
    private boolean initialize;   // храним готовность к покупкам
    private BillingProcessor bp;  // переменная нашего процессора

    private PreferencesManager prefManager; // класс, который работает с SharedPreferences. Я для себя решил вынести всю логику отдельно
    private Resources resources;            // для работы с ресурсами. Раз получаем и постоянно обращаемся
    private Switch tbAdsState;        // кнопка

    Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        // инициализируем `BillingProcessor`. В документации на `GitHub` сказано, что для защиты от липовых покупок через приложения типа
        // `freedom` необходимо в конструктор `BillingProcessor`'а передать еще и свой `MERCHANT_ID`. Где его взять - внизу текущего ответа опишу шаги
        bp = new BillingProcessor(context,
                InAppBillingResources.getRsaKey(), InAppBillingResources.getMerchantId(), this);

        prefManager = new PreferencesManager(context); // класс, который работает с `SharedPreferences`
        adsStatus = prefManager.getAdsStatus();        // получаем из `SharedPreferences` сохраненное состояние рекламы (ВКЛ / ВЫКЛ)
        resources = context.getResources();            // получаем "доступ" к ресурсам
    }

    public static SettingsFrag newInstance() {
        Bundle args = new Bundle();
        SettingsFrag fragment = new SettingsFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // создаем `View` экрана настроек
        final View settView = inflater.inflate(R.layout.frag_sett_screen, container, false);

        // инициализация других полей
        tbAdsState = settView.findViewById(R.id.tbAdsState);
        // инициализация других полей

        // вешаем слушателя нажатий по кнопке `ToggleButton`
        tbAdsState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // `adsStatus` : getAdsStatus из класса `PreferencesManager`
                // true - enabled (ВКЛ)  | false - disabled (ВЫКЛ)
                if (adsStatus && initialize) {
                    bp.purchase(getActivity(), InAppBillingResources.getSKU_Disable_Ads());
                }

                if (!adsStatus) {
                    tbAdsState.setChecked(false);
                    Toast.makeText(getActivity(),
                            getActivity().getResources().getString(R.string.txt_ads_is_already_disabled),
                            Toast.LENGTH_LONG).show();
                }

            }
        });
        if (!adsStatus)
            tbAdsState.setVisibility(View.INVISIBLE);

        return settView;
    }

    @Override
    public void onResume() {
        super.onResume();
        tbAdsState.setChecked(prefManager.getAdsStatus());
    }

    // диалог, который скажет пользователю, что после покупки необходимо перезагрузиться
    private void restartDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(resources.getString(R.string.msg_notification_Title));
        builder.setCancelable(false);
        builder.setPositiveButton("Ок",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        restartApp();
                        dialog.cancel();
                    }
                }
        );
        AlertDialog resetDialog = builder.create();
        resetDialog.show();

    }

    // перезагружаем приложение
    private void restartApp() {
        Intent rIntent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
        if (rIntent != null) {
            rIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            mContext.startActivity(rIntent);
        }
    }

    // ... другие методы класса
    // [START billing part of class]

    @Override
    public void onProductPurchased(@NonNull String productId, TransactionDetails details) {
        // Called when requested PRODUCT ID was successfully purchased
        // Вызывается, когда запрашиваемый PRODUCT ID был успешно куплен

        if (bp.isPurchased(productId)) {
            prefManager.setAdsStatus(false); // 1. записываем в `SharedPreferences` состояние рекламы (ВЫКЛ / false)
            tbAdsState.setChecked(false);    // 2. устанавливаем `TogglButton` в соответствующее состояние
            restartDialog(); // 3. перезагружаем приложение
        } else {
            tbAdsState.setChecked(true); // иначе, если не прошла покупка, оставляем состояние (ВКЛ / true)
        }
    }




    @Override
    public void onPurchaseHistoryRestored() {
        //Вызывается, когда история покупки была восстановлена,
        // и список всех принадлежащих идентификаторы продуктов был загружен из Google Play

        // так Вы сможете НУЖНУЮ покупку проверить
        for (String sku : bp.listOwnedProducts()) {
            //MyAppLogs.show("Owned Managed Product: " + sku);
            boolean wasBouhtg = sku.equals(InAppBillingResources.getSKU_Disable_Ads());
            if (wasBouhtg) {
                // true - куплено
                // пишем в `SharedPreferences`, что отключили рекламу
            } else {
                // false - не куплено
                // пишем в `SharedPreferences`, что нужно показывать рекламу
            }
        }
    }

    @Override
    public void onBillingError(int errorCode, Throwable error) {
        // Вызывается, когда появляется ошибка. См. константы класса
        // для получения более подробной информации
    }

    @Override
    public void onBillingInitialized() {
        // Вызывается, когда bp был инициализирован и он готов приобрести
        initialize = true;
    }
    // [END billing part of class]

}