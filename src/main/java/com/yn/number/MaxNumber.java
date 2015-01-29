package com.yn.number;

import java.util.Arrays;
import java.util.Random;

/**
 * Copyright (C), nanyang205380@sohu-inc.com.
 * 
 * @ClassName: MaxNumber
 * @Description: 从Ｎ个元素中找出Ｍ个最大的数
 * @author YangNan(杨楠)
 * @date 2015年1月5日 下午3:05:02
 */
public class MaxNumber {
	public static void main(String[] args) {
		int[] src = new int[100];
		int[] arrs = new int[6];
		Random r = new Random();
		for (int i = 0; i < src.length; ++i) {
			src[i] = r.nextInt(1000000);
		}

		for (int i = 0; i < src.length; ++i) {
			L: for (int j = 0; j < arrs.length; ++j) {
				if (src[i] > arrs[j] && j != arrs.length - 1) {
					// 从j位置开始移动
					move(arrs, j);
					arrs[j] = src[i];
					break L;
				}
			}
		}
		
		Arrays.sort(src);
		
		System.out.println(Arrays.toString(src));
		System.out.println(Arrays.toString(arrs));
	}

	// 从index的位置逐个往后移动
	private static void move(final int[] arrs, int index) {
		for (int j = arrs.length - 1; j >= index + 1; j--) {
			arrs[j] = arrs[j - 1];
		}
	}
}
