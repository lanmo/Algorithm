package com.yn.string;

/**
 * Created by yangnan on 2017/5/27.
 * 回文数判断
 */
public class Palindrome {

    public static void main(String[] args) {
        String str = "aba";

        System.out.println(isPalindrome(str));
    }

    /**
     * 回文数判断
     * <p>
     *     aba
     *     abcdcba
     *     aa
     *     逻辑从中间往两边进行查找
     * </p>
     *
     * @param str
     * @return
     */
    public static boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }

        int length = (str.length() -1) >> 1;
        char[] chars = str.toCharArray();
        for (int i=0; i<length; i++) {
            if (chars[i] != chars[str.length() - 1 - i]) {
                return false;
            }
        }

        return true;
    }
}
