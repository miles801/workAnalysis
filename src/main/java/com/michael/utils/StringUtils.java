package com.michael.utils;

/**
 * @author Michael
 */
public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }
}
