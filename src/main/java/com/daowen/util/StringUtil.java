package com.daowen.util;

import java.util.regex.Pattern;

public class StringUtil {

    private static final Pattern CHINESE_PATTERN = Pattern.compile("[\\u4e00-\\u9fa5]");

    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNumeric(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches("-?\\d+");
    }

    public static boolean containChinese(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return CHINESE_PATTERN.matcher(str).find();
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    public static boolean equals(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        }
        if (str1 == null || str2 == null) {
            return false;
        }
        return str1.equals(str2);
    }
}