package com.example.demo_tvnews.util;

public class StringUtils {
    public static boolean isEmpty(String string) {
        if (string == null || string.length() <= 0) {
            return true;
        } else {
            return false;
        }
    }
}
