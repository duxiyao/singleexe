package com.dxy.util;

import java.text.DecimalFormat;

public class TypeUtil {
    public static int parseInt(String o) {
        try {
            return (int) Double.parseDouble(o);
        } catch (Exception e) {
        }
        return 0;
    }

    public static double parseDouble(String o) {
        try {
            return Double.parseDouble(o);
        } catch (Exception e) {
        }
        return 0;
    }

    public static String maxDoublePrice(String a, String b) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        try {
            double x = parseDouble(a);
            double y = parseDouble(b);
            return decimalFormat.format(Math.max(x, y));
        } catch (Exception e) {
        }
        return "-";
    }
}
