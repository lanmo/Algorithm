package com.yn.number;

/**
 * Copyright (C), nanyang205380@sohu-inc.com.
 * @ClassName: MaxSum   
 * @Description: 计算数组中子数组之和的最大值
 * @author YangNan(杨楠)
 * @date 2015年3月23日 上午11:01:04 
 */
public class MaxSum {
	public static void main(String[] args) {
		int[] arrays = {1,-2,3,5,-3,2};//8
		arrays = new int[]{0,-2,3,5,-1,2};//9
		arrays = new int[]{-9,-2,-3,-5,-3};//-2
		System.out.println(maxSum(arrays,arrays.length));
		System.out.println(maxSum2(arrays, arrays.length));
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: maxSum   
	 * @Description: 时间复杂度O(N2)   
	 */
	public static int maxSum(final int[] arrays, int n) {
		int max = Integer.MIN_VALUE;
		int sum = 0;
		for(int i=0; i<n; ++i) {
			sum = 0;
			for(int j=i; j<n; ++j) {
				sum += arrays[j];
				if(sum > max) {
					max = sum;
				}
			}
		}
		
		return max;
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: maxSum   
	 * @Description: 时间复杂度O(n)
	 */
	public static int maxSum2(final int[] arrays, int n) {
		int start = arrays[n-1];
		int all = arrays[n-1];
		for(int i=n-2; i>=0; --i) {
			if(start < 0)
				start = 0;
			start += arrays[i];
			if(start > all)
				all = start;
		}
		return all;
	}
}
