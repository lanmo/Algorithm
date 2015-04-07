package com.yn.number;

import java.util.Arrays;
import java.util.Random;

/** 
 * 洗牌算法,思路：从数组Ｎ个中随机取一个数和最后一个进行交换，
 * 然后在从Ｎ-1中随机取一个数和倒数第二个进行交换，依此类推............
 * */
public class Shuffle {
	public static void main(String[] args) {
		int[] arrays = new int[54];
		for(int i=0; i<arrays.length; ++i) {
			arrays[i] = i+1;
		}
		
		int times = 53;
		while (times != 0) {
			Random r = new Random();
			int pre = r.nextInt(times);
			swap(arrays, pre, times);
			times--;
		}
		
		System.out.println(Arrays.toString(arrays));
		
	}
	
	private static void swap(final int[] arrays, int a, int b) {
		int temp = arrays[a];
		arrays[a] = arrays[b];
		arrays[b] = temp;
	}
}
