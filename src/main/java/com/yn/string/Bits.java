package com.yn.string;

/**
 * Created by yangnan on 17/4/7.
 * 位操作
 */
public class Bits {
    public static void main(String[] args) {
        computeBit1(7);
    }

    /**
     * <P>
     *     题目:求整数的二进制表示中有多少个 1
     *     方法:应用了n&=(n-1)能将 n 的二进制表示中的最右边的 1 翻转为 0 的事实。
     *     只需要不停地执行 n&=(n-1)，直到 n 变成 0 为止，那么翻转的次数就是原来的 n 的二进制表示中 1 的个数
     * </P>
     */
    public static void computeBit1(int n) {
        int count = 0;
        while(n!=0){
            count++;
            n = n & (n-1);
        }

        System.out.printf("二进制中1的个数:" + count);
    }
}
