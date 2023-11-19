package com.example.du_an_1.funtions;

import java.text.NumberFormat;
import java.util.Locale;

public class MoneyFormat {

    public static String moneyFormat(double price){
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(localeVN);
        return currencyFormat.format(price);
    }
}
