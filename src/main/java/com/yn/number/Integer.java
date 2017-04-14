package com.yn.number;

/**
 * Created by yangnan on 17/4/14.
 *
 */
public class Integer {

    public static void main(String[] args) {

        java.lang.Integer s = java.lang.Integer.MAX_VALUE;

        System.out.println(parseInt("-2147483f48"));
    }

    /**
     * 将字符串转换成int
     * <p>
     *     要考虑溢出问题
     * </p>
     *
     * @param str
     * @return
     */
    public static int parseInt(String str) {

        if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("参数不合法");
        }

        int maxDiv = java.lang.Integer.MAX_VALUE / 10;
        int minDiv = -(java.lang.Integer.MIN_VALUE / 10);
        int maxM = java.lang.Integer.MAX_VALUE % 10;
        int minM = -(java.lang.Integer.MIN_VALUE % 10);

        int result = 0; //用于存放结果

        char ch = str.charAt(0); //当前字符
        boolean sign = true;//true表示正数 false表示负数
        int i = 0, len = str.length();

        if (ch == '+' || ch == '-') {
            if (len == 1) {
                throw new IllegalArgumentException("参数不合法");
            }

            if (ch == '-') {
                sign = false;
            }

            i++;
        }

        while (i < len) {
            int digit = str.charAt(i++) - '0';
            if (digit >= 0 && digit <= 9) {
                //正数溢出
                if (sign && (result > maxDiv || (result == maxDiv && digit > maxM))) {
                    throw new IllegalArgumentException("number overflow");
                }

                //负数溢出
                if (!sign && (result > minDiv || (result == minDiv && digit > minM))) {
                    throw new IllegalArgumentException("number overflow");
                }

                result = result * 10 + digit;
            } else {
                throw new IllegalArgumentException("number input error");
            }
        }

        return sign ? result : -result;
    }
}
