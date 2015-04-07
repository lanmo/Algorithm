package com.yn.number;

import java.util.Arrays;
import java.util.Comparator;
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
		Integer[] src = new Integer[100];
		int k = 6;
		Random r = new Random();
		for (int i = 0; i < src.length; ++i) {
			src[i] = r.nextInt(1000000);
		}
		
		Arrays.sort(src, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		
		System.out.println(Arrays.toString(src));
		
		//利用小顶堆的思想,创建一个大小为k的数组,data[0]为小顶堆的堆顶，为所求的第k大的数
		int[] data =  new int[k];
		for(int i=0; i<src.length; i++) {
			if(src[i] > data[0]) {
				data[0] = src[i];
				KBig(data, data.length);
			}
		}
		
		System.out.println(Arrays.toString(data));
		System.out.println(data[0]);
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: KBig   每个元素data[i],父节点data[i/2]，左叶子节点data[i*2 + 1];右叶子节点data[i*2 + 2];
	 */
	public static void KBig(final int[] data, int K) {
		int startIndex = 0;//父节点
		int left = 0;//左叶子节点
		while(startIndex < K) {
			left = 2 * startIndex + 1;
			if (left >= K) 
				break;
			if((left < K-1) && (data[left + 1] < data[left])) {
				//如果右叶子节点<左叶子节点
				left = left + 1;
			}
			if(data[left] < data[startIndex]) {
				int temp = data[startIndex];
				data[startIndex] = data[left];
				data[left] = temp;
				startIndex = left;
			} else {
				break;
			}
		}
	}
}
