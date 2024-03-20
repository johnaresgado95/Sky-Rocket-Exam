package com.example.simple_ecommerce.data.util;

import java.text.DecimalFormat;

public class DisplayUtil {
    public static String computeSale(int price, double percentSale) {
        DecimalFormat formatter = new DecimalFormat("#,###,###.##");
        return formatter.format(price * (percentSale / 100.0));
    }
}
