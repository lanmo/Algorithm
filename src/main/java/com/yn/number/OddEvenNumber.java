package com.yn.number;

import java.util.Arrays;
import java.util.Random;

/**
 * Copyright (C), nanyang205380@sohu-inc.com.
 * @ClassName: OddEvenNumber   
 * @Description:一个数组中奇数在左边偶数在右边
 * @author YangNan(杨楠)
 * @date 2015年3月5日 上午11:50:52 
 */
public class OddEvenNumber {
	
	public static void main(String[] args) {
		int[] arrays = new int[20];
		for(int i=0;i<arrays.length; ++i) {
			Random r = new Random();
			arrays[i] = r.nextInt(100);
		}
		System.out.println("结果:" + Arrays.toString(arrays));
		odd(arrays);
		System.out.println("结果:" + Arrays.toString(arrays));
	}

	/**
	 * @author: YangNan(杨楠)  
	 * @date: 2015年3月5日 上午11:53:51 
	 * @Title: odd   
	 * @Description: 数组中奇数在左边偶数在右边
	 * @throws:
	 */
	public static void odd(final int[] arrays) {
		int[] array = arrays;
		int j=0, t = arrays.length-1;
		for(int i=0;i < arrays.length; ++i) {
			if((arrays[i] & 1) == 0) {
				array[t--] = arrays[i];
			} else {
				array[j++] = arrays[i];
			}
		}
	}
}
