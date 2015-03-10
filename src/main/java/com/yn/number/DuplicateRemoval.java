package com.yn.number;

import java.util.Arrays;

/**
 * Copyright (C), nanyang205380@sohu-inc.com.
 * @ClassName: DuplicateRemoval   
 * @Description: 数组去重,可以用set去重，这里用循环来做
 * @author YangNan(杨楠)
 * @date 2015年3月10日 上午9:44:47 
 */
public class DuplicateRemoval {
	public static void main(String[] args) {
		int[] arrays = {4,2,4,6,1,2,4,7,8};
		System.out.println("元数组:" + Arrays.toString(arrays));
		System.out.println("去重数组:" + Arrays.toString(removal(arrays)));
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @date: 2015年3月10日 上午9:45:36 
	 * @Title: removal   
	 * @Description: 去重   
	 */
	public static int[] removal(final int[] arrays) {
		
		boolean[] arrs = new boolean[arrays.length];
		for(int i=0; i<arrays.length; ++i) {
			if(arrs[i])
				continue;
			for(int j=i+1; j<arrays.length; ++j) {
				if(arrays[i] == arrays[j]) {
					arrs[j] = true;
				}
			}
		}
		
		int count = 0;
		for(int i=0; i < arrs.length; ++i) {
			if(!arrs[i])
				count++;
		}
		
		int[] result = new int[count];
		count = 0;
		for(int i=0; i < arrs.length; ++i) {
			if(!arrs[i])
				result[count++] = arrays[i];
		}
		
		return result;
	}
}
