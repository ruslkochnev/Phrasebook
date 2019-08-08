package com.luc1fer.phrasebook;

public class InAppBillingResources {

    // Ваш `RSA` ключ из `Google Play Developer Console`
    private static final String RSA_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlZk7WZNYAPUDu8qRWjLgXdNkXKZzUvqGfnyEP/k1oZA5NziT+d30q/WGRqlcAbVm+RaERMph/Bmbqz5RIhqfeGXgUZTUFU+IY2xB3OnGXvo6DyGbfO+z/l7Ki/lT1oTKUhKnBz58TUtw3WlnhMgLWcWP+CCEhncaS1PnTJ0m/BgmA9khDSsqsXlaLGDonzoeBhDGs448mqp2kw+xBfgAmmo/dGsfm4p6q+mxFwCcHdqLok05VudzpUmNdC6HEx9TWP6XxzP3dKBG6J3hQkwRj4igySx0ZvBlHwI1U4m6sG5CP4wtmQUlKYNdRsacQM65SGqLkP8B75l4diUfcvb0rQIDAQAB";
    private static final String MERCHANT_ID = "17790155835943453312";           // Ваш `MERCHANT_ID` из `Google Play Developer Console`
    private static final String SKU_DISABLE_ADS = "disable_ad";          // Ваш `product_id`, создается в `Google Play Developer Console`


    public static String getRsaKey() {
        return RSA_KEY;
    }

    public static String getMerchantId() {
        return MERCHANT_ID;
    }

    public static String getSKU_Disable_Ads() {
        return SKU_DISABLE_ADS;
    }
}