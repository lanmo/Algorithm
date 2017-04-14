package com.yn.string;

/**
 * Created by yangnan on 17/4/5.
 * 全排列
 *  题目：
 *  <p>
 *      设计一个算法，输出一个字符串字符的全排列。
 *      比如，String = "abc"
 *      输出是"abc","bac","cab","bca","cba","acb"
 *  </p>
 */
public class Permutation {

    public static void main(String[] args) {
        String str = "abc";
        permutation(str.toCharArray(), 0, str.length() - 1);
    }

    /**
     * 算法思想:
     * <p>
     *     从集合依次选出每一个元素，作为排列的第一个元素，然后对剩余的元素进行全排列，如此递归处理；
            比如：首先我要打印abc的全排列，就是第一步把a 和bc交换（得到bac,cab），这需要一个for循环，
            循环里面有一个swap，交换之后就相当于不管第一步了，进入下一步递归，所以跟一个递归函数，
            完成递归之后把交换的换回来，变成原来的字串 递归方法：
             abc 为例子：
             1. 固定a, 求后面bc的全排列： abc, acb。 求完后，a 和 b交换； 得到bac,开始第二轮
             2. 固定b, 求后面ac的全排列： bac, bca。 求完后，b 和 c交换； 得到cab,开始第三轮
             3. 固定c, 求后面ba的全排列： cab, cba
     * </p>
     *
     * @param chars
     * @param start
     * @param end
     */
    private static void permutation(char[] chars, int start, int end) {
        if (end == 1) {
            return;
        }

        if (start == end) {
            System.out.println(chars);
        } else {
            for (int i=start; i<=end; ++i) {
                swap(chars, i, start);
                permutation(chars, start+1, end);
                swap(chars, start, i);
            }
        }

    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
