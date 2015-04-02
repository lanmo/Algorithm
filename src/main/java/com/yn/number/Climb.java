package com.yn.number;
import java.util.Scanner;

/**
 * Copyright (C), nanyang205380@sohu-inc.com.
 * @ClassName: Climb   
 * @Description: 楼梯共有n级台阶。小明每一步最少爬1级台阶，最多能爬m级台阶。
			例如，楼梯有3级台阶，小明每一步可以爬1级、2级或3级，则小明一共有如下4种爬法。
			（1,1,1）（1,2）（2,1）（3）
			如果n的取值从32~36，m的取值从2~3，请写程序输出每种情况下小明有多少种爬楼梯的方法。
			输入格式：共2行数据，内容如下：
			10 32 32 33 33 34 34 35 35 36 36
			10 2 3 2 3 2 3 2 3 2 3
			每行第一个元素表示该行输入数值的个数为10个。
			第一行的第2~11个数表示n的取值
			第二行的第2~11个数表示对应第一行n取值的m取值。
			输出格式：共10行数据，每行1个数值，表示爬楼梯的方法总数。
 * @author YangNan(杨楠)
 * @date 2015年4月2日 下午3:30:59 
 */
public class Climb {
	public static void main(String[] args) {
		String str = "10 32 32 33 33 34 34 35 35 36 36";
		String ss = "10 2 3 2 3 2 3 2 3 2 3";
		Scanner sc = new Scanner(System.in);
		String str1 = sc.nextLine();
		String str2 = sc.nextLine();
		int arr1[] = getarr(str1);
		int arr2[] = getarr(str2);
		for (int i = 0; i < arr1.length; i++) {
			System.out.println(getStepNum(arr1[i], arr2[i]) + "");
		}
	}

	private static int[] getarr(String str) {
		String s[] = str.split(" ");
		int[] arr = new int[s.length - 1];
		int index = 0;
		for (int i = 1; i < s.length; i++) {
			arr[index++] = Integer.parseInt(s[i]);
		}
		return arr;
	}

	private static int getStepNum(int n, int m) {
		int sumStep = 0;
		if (n == 0) {
			return 1;
		}
		
		int i = 1;
		if (n >= m) {
			for (; i <= m; i++) {
				sumStep += getStepNum(n - i, m);
			}
		} else {
			sumStep = getStepNum(n, n);
		}
		return sumStep;
	}

}
