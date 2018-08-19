package com.itxiaoer.dimg.constant;

/**
 * @author : liuyk
 */

public final class NumberUtil {

    public static int intVal(String num) {
        return intVal(num, 0);

    }

    public static int intVal(String num, int defaultVal) {
        if (num != null && !num.isEmpty()) {

            try {
                return Integer.parseInt(num);
            } catch (Exception e) {
                return defaultVal;
            }
        }
        return defaultVal;
    }


    public static long longVal(String num) {
        return longVal(num, 0);

    }

    public static long longVal(String num, long defaultVal) {
        if (num != null && !num.isEmpty()) {

            try {
                return Long.parseLong(num);
            } catch (Exception e) {
                return defaultVal;
            }
        }
        return defaultVal;
    }


}
