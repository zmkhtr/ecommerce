package com.aflowz.ecommerce.Utils;

import android.util.Log;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import timber.log.Timber;

public class MainUtils {
    public static void logErrorMessage(String message, Throwable throwable) {
        Timber.e(throwable, message);
    }

    public static void logSuccessMessage(String message) {
        Timber.d(message);
    }

    public static String cutImageUrl(String url) {
        String urlFirst = url.replace("[", "");
        String urlSecond = urlFirst.replace("\\", "");
        String urlEnd = urlSecond.replace("\"", "");
        return urlEnd.substring(0, urlEnd.length() - 1);
    }


    public static String cutPriceString(String url) {
//        "1 Hari (100000)",
//        "2 Hari (150000)"
        Timber.d("Harga %s", url);
        String urlZero = url.substring(0,2);
        Timber.d("Harga zero %s", urlZero);
        String urlFirst = url.replace(urlZero,"");
        Timber.d("Harga urlFirst %s", urlFirst);
        String urlSecond = urlFirst.replace("(", "");
        Timber.d("Harga urlSecond %s", urlSecond);
        String urlThird = urlSecond.replace("H", "");
        Timber.d("Harga urlThird %s", urlThird);
        String urlFourth = urlThird.replace("a", "");
        Timber.d("Harga urlFourth %s", urlFourth);
        String urlFive = urlFourth.replace("r", "");
        Timber.d("Harga urlFive %s", urlFive);
        String urlSix = urlFive.replace("i", "");
        Timber.d("Harga urlSix %s", urlSix);
        String urlEnd = urlSix.replace(")", "");
        Timber.d("Harga urlEnd %s", urlEnd);
        return formatRupiah(urlEnd);
    }

    public static String cutPriceCart(String price){
        Timber.d("Harga %s", price);
        String urlZero = price.substring(0,2);
        Timber.d("Harga zero %s", urlZero);
        String urlFirst = price.replace(urlZero,"");
        Timber.d("Harga urlFirst %s", urlFirst);
        String urlSecond = urlFirst.replace("(", "");
        Timber.d("Harga urlSecond %s", urlSecond);
        String urlThird = urlSecond.replace("H", "");
        Timber.d("Harga urlThird %s", urlThird);
        String urlFourth = urlThird.replace("a", "");
        Timber.d("Harga urlFourth %s", urlFourth);
        String urlFive = urlFourth.replace("r", "");
        Timber.d("Harga urlFive %s", urlFive);
        String urlSix = urlFive.replace("i", "");
        Timber.d("Harga urlSix %s", urlSix);
        String urlEnd = urlSix.replace(")", "");
        Timber.d("Harga urlEnd %s", urlEnd);
        return urlEnd;
    }

    public static String cutPrice(String price){
        String urlFirst = price.replace("Rp","");
        String urlSecond = urlFirst.replace(".","");
        String urlThird = urlSecond.replace(",","");
        return urlThird.substring(0, urlThird.length() - 2);
    }

    public static String formatRupiah(String price){

        Timber.d("Harga format rupiah %s", price);
        String priceCut = price.replace(",", "");
        double priceDouble = Double.parseDouble(priceCut);

        DecimalFormat exchangeIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        exchangeIndonesia.setDecimalFormatSymbols(formatRp);
        Timber.d("Price rupiah: %s", exchangeIndonesia.format(priceDouble));
        return exchangeIndonesia.format(priceDouble);
    }
}
