package com.yn;

/**
 *
 *（0，3）
 （0，2）（1，3）
 （0，1）（1，2）（2，3）
 （0，0）（1，1）（2，2）（3，3）
 （1，0）（2，1）（3，2）
 （2，0）（3，1）
 （3，0）

 {1, 2,    3,      4},
 {5, 6,   7,      8},
 {9, 10, 11,   12},
 {13, 14, 15,  16}
 *
 */
public class Juzheng {
  
    private static final int[][] DATA = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};  
  
    public static void main(String[] args) {  

//        title1();
        title2();
//        title3();
    }

    public static void title1() {
        int n = DATA.length;
        int iStart = 0;
        int jStart = n - 1;

        while (iStart != n) {
            for (int i = iStart, j = jStart; i <= n - 1 && j <= n - 1; i++, j++) {
                System.out.print(DATA[i][j]);
                System.out.print(" ");
            }
            if (jStart > 0) {
                jStart--;
            } else {
                iStart++;
            }
            System.out.println();
        }
    }


    public static void title2() {
        //只打印了上半部分
        int n = DATA.length;
        for (int i=0; i<n; i++) {
            for (int j=0; j<=i; j++) {
                System.out.print(DATA[j][i-j]);
                System.out.print(" ");
            }
            System.out.println();
        }

        for (int i=1; i<n; i++) {
            for (int j=n-1; j>=i; j--) {
                System.out.print(DATA[i + n-1-j][j] + " ");
            }
            System.out.println();
        }
    }

}