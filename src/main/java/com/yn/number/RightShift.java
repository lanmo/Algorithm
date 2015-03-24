package com.yn.number;

import java.util.Arrays;

/**
 * Copyright (C), nanyang205380@sohu-inc.com.
 * @ClassName: RightShift   
 * @Description: 数组循环移位
 * 设计算法,把一个含有N个元素的数组循环向右移K位,要求时间复杂度为O(N),并且只允许有两个附加变量
 * @author YangNan(杨楠)
 * @date 2015年3月23日 下午3:15:17 
 */
public class RightShift {
	public static void main(String[] args) {
		int k = 9;
		int[] arrays = {1,2,3,4,5,6,7};
		rightShift(arrays, arrays.length, k);
		System.out.println(Arrays.toString(arrays));
		arrays = new int[]{1,2,3,4,5,6,7};
		rightShift2(arrays, arrays.length, k);
		System.out.println(Arrays.toString(arrays));
		arrays = new int[]{1,2,3,4,5,6,7};
		rightShift3(arrays, arrays.length, k);
		System.out.println(Arrays.toString(arrays));
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: rightShift   
	 * @Description:   时间复杂度O(n * k),当k>n时,复杂度大于O(n * n)
	 */
	public static void rightShift(final int[] arr, int n, int k) {
		while(k-- > 0) {
			int t = arr[n-1];
			for(int i=n-1; i>0; --i) {
				arr[i] = arr[i-1];
			}
			arr[0] = t;
		}
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: rightShift2  
	 * @Description:   时间复杂度O(n * n)较上面一种有点改进,跟K没有关系
	 */
	public static void rightShift2(final int[] arr, int n, int k) {
		k %= n;
		while(k-- > 0) {
			int t = arr[n-1];
			for(int i=n-1; i>0; --i) {
				arr[i] = arr[i-1];
			}
			arr[0] = t;
		}
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: rightShift3
	 * 例如：数组{1,2,3,4,5,6,7},数组长度7(n)，右移２(k)位
	 * 1.逆序排列(0~n-k-1)：{5,4,3,2,1,6,7}
	 * 2.逆序排列(n-k,n-1)：{5,4,3,2,1,7,6}
	 * 3.逆序排列(0~n-1) : {6,7,1,2,3,4,5}
	 * @Description:   线性时间内完成右移
	 */
	public static void rightShift3(final int[] arr, int n, int k) {
		 k %= n;
		 //第一步 逆序排列(0~n-k-1)个元素
		 reverse(arr, 0, n-k-1);
		 //第二步　逆序排列(n-k~n-1)个元素
		 reverse(arr, n-k, n-1);
		 //第三步　逆序排列(0~n-1)个元素
		 reverse(arr, 0, n-1);
	}
	
	public static void reverse(final int[] arr, int i, int j) {
		for(; i<j; i++,j--) {
			int temp = arr[j];
			arr[j] = arr[i];
			arr[i] = temp;
		}
	}
}
