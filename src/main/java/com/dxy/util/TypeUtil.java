package com.dxy.util;

public class TypeUtil {
    public static int parseInt(String o) {
        try {
            return (int) Double.parseDouble(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
