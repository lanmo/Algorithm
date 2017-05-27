package com.yn;

/**
 * Created by yangnan on 2017/5/27.
 */
public class Test {

    public static void main(String[] args) {
        int[] arys = {1,2,2,2,3};
        System.out.println(ef2(arys, 2));
    }

//    public void test() {
//        int[] arys = {1, 2, 4, 7, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 14, 22, 56};
//        System.out.println(ef2(arys, 10));
//    }

    private static int ef(int[] arys, int target) {
        int low = 0;
        int high = arys.length - 1;
        while (low <= high) {
            int mdl = low + ((high - low) / 2);
            if (target == arys[mdl]) {
                return mdl;
            } else if (target < arys[mdl]) {
                low = mdl - 1;
            } else {
                high = mdl + 1;
            }
        }
        return -1;
    }

    private static int ef2(int[] arys, int target) {
        int low = 0;
        int high = arys.length - 1;
        while (low <= high) {
            int mdl = low + ((high - low) / 2);
            if (target == arys[mdl]) {
                if (mdl == 0 || target != arys[mdl-1]) {
                    return mdl;
                }

                high = mdl - 1;
            } else if (target < arys[mdl]) {
                high = mdl - 1;
            } else {
                low = mdl + 1;
            }
        }
        return -1;
    }

}
