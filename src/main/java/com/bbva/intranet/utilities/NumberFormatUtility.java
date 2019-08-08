package com.bbva.intranet.utilities;

import java.text.NumberFormat;
import java.util.Locale;

public abstract class NumberFormatUtility {

    public static String currencyFormat(Double currencyValue, Locale locale) {
        String currencyFormatted;

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        if (currencyValue != null) {
            currencyFormatted = currencyFormat.format(currencyValue);
        } else {
            currencyFormatted = currencyFormat.format(0);
        }

        return currencyFormatted;
    }

    public static String percentFormat(Double percentValue, int maxFractionDigits, Locale locale) {
        String percentFormatted;

        NumberFormat percentFormat = NumberFormat.getPercentInstance(locale);
        percentFormat.setMaximumFractionDigits(maxFractionDigits);
        if (percentValue != null) {
            percentFormatted = percentFormat.format(percentValue);
        } else {
            percentFormatted = percentFormat.format(0);
        }

        return percentFormatted;
    }

}
