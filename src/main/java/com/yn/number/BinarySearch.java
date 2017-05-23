package com.yn.number;


/**
 * Copyright (C), nanyang205380@sohu-inc.com.
 * @ClassName: BinarySearch   
 * @Description: 二分法查找算法
 * @author YangNan(杨楠)
 * @date 2015年1月29日 下午2:41:13 
 */
public class BinarySearch {
	
	public static void main(String[] args) {
		int[] array = new int[1000];
		for(int i=0; i<array.length; ++i) {
			array[i] = i;
		}
		System.out.println(binarySearch(array, 0, array.length, 2));
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @date: 2015年1月29日 下午2:43:45 
	 * @Title: binarySearch   
	 * @Description: 二分法查找  
	 * @throws:
	 */
	public static int binarySearch(final int[] sources, int fromIndex, int toIndex, int key) {
		
		int low = fromIndex;
		int high = toIndex - 1;
		
		while (low <= high) {
//			int mid = (low + high) >>> 1;
			int mid = low + ((high - low) >>> 1);
			int middle = sources[mid];
			if(middle > key) {
				high = mid - 1;
			} else if(middle < key) {
				low = mid + 1;
			} else
				return mid;
		}
		
		return -1;
	}
}
