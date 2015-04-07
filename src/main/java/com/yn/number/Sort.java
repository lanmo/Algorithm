package com.yn.number;

import java.util.Arrays;
import java.util.Random;

/**
 * Copyright (C), nanyang205380@sohu-inc.com.
 * @ClassName: Sort   
 * @Description: 排序算法
 * @author YangNan(杨楠)
 */
public class Sort {
	public static void main(String[] args) {
		int[] arrays = new int[10];
		for(int i=0; i<arrays.length; ++i) {
			arrays[i] = new Random().nextInt(100);
		}
		
		System.out.println("原数组:" + Arrays.toString(arrays));
		bubbleSort(arrays);
		quickSort(arrays, 0, arrays.length-1);
		System.out.println("快速排序:" + Arrays.toString(arrays));
		insertionSort(arrays);
		selectSort(arrays);
		
		buildMaxHeapify(arrays);
		heapSort(arrays);
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: Bubble   
	 * @Description: 冒泡   
	 */
	public static void bubbleSort(final int[] arrays) {
		//趟数
		for(int i=0; i<arrays.length-1; ++i ) {
			for(int j=i+1; j<arrays.length; ++j) {
				if(arrays[i] > arrays[j]) {
					//从小到大排序
					int temp = arrays[i];
					arrays[i] = arrays[j];
					arrays[j] = temp;
				}
			}
		}
		
		System.out.println("冒泡排序:" + Arrays.toString(arrays));
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: quickSort   
	 * @Description: 快速排序   
	 * 该方法的基本思想是：
	 *		1．先从数列中取出一个数作为基准数。
     *		2．分区过程，将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边。
	 *		3．再对左右区间重复第二步，直到各区间只有一个数。
	 */
	public static void quickSort(final int[] arrays, int left, int right) {
		if(left >= right) return;
		int first = left;
		int last = right;
		int key = arrays[left];/*用字表的第一个记录作为枢轴*/
		while(first < last) {
			//从右边往左边查找
			while(first < last && arrays[last] >= key) --last;
				arrays[first] = arrays[last];/*将比第一个小的移到低端*/
			//从左边往右边查找
			while(first < last && arrays[first] <= key) ++first;
				arrays[last] = arrays[first];/*将比第一个大的移到高端*/
		}
		
		arrays[left] = key;/*枢轴记录到位*/
		quickSort(arrays, left, first -1);/*对左边的元素进行排序*/
		quickSort(arrays, first+1, right);/*对右边的元素进行排序*/
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: insertSort   
	 * @Description: 插入排序
	 * 插入排序就是每一步都将一个待排数据按其大小插入到已经排序的数据中的适当位置，直到全部插入完毕
	 * 设数组为a[0…n-1]。
	 *		1.      初始时，a[0]自成1个有序区，无序区为a[1..n-1]。令i=1
	 *		2.      将a[i]并入当前的有序区a[0…i-1]中形成a[0…i]的有序区间。
	 *		3.      i++并重复第二步直到i==n-1。排序完成。 
	 * @return: void    返回类型   
	 */
	public static void insertionSort(final int[] arrays) {
		 // 即每次a[i]先和前面一个数据a[i-1]比较，如果a[i] > a[i-1]说明a[0…i]也是有序的，无须调整。
		//否则就令j=i-1,temp=a[i]。然后一边将数据a[j]向后移动一边向前搜索，当有数据a[j]<a[i]时停止并将temp放到a[j + 1]处
		int j=0;
		for(int i=1; i< arrays.length; ++i) {
			if(arrays[i] < arrays[i-1]) {
				int temp = arrays[i];
				for(j =i-1; j>=0 && arrays[j] > temp; --j)
					arrays[j+1] = arrays[j];
				arrays[j+1] = temp;
			}
		}
		
		System.out.println("插入排序:" + Arrays.toString(arrays));
		
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: selectSort   
	 * @Description: 选择排序
	 * 比如在一个长度为N的无序数组中，在第一趟遍历N个数据，
	 * 找出其中最小的数值与第一个元素交换，第二趟遍历剩下的N-1个数据，
	 * 找出其中最小的数值与第二个元素交换......第N-1趟遍历剩下的2个数据，找出其中最小的数值与第N-1个元素交换，至此选择排序完成   
	 */
	public static void selectSort(final int[] arrays) {
		
		for(int i=0; i<arrays.length-1; ++i) {
			int index = i;
			for(int j= i+1; j<arrays.length; ++j) {
				if(arrays[j] < arrays[index]) {
					index = j;
				}
				
				if(index != i) {//交换元素
					int temp = arrays[i];
					arrays[i] = arrays[index];
					arrays[index] = temp;
				}
			}
		}
		
		System.out.println("选择排序:" + Arrays.toString(arrays));
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: buildMaxHeapify   
	 * @Description: 建立大顶堆(或者小顶堆)  
	 * 一般都用数组来表示堆，i结点的父结点下标就为(i – 1) / 2。它的左右子结点下标分别为2 * i + 1和2 * i + 2。如第0个结点左右子结点下标分别为1和2。 
	 */
	public static void buildMaxHeapify(final int[] arrays) {
		//没有子节点的才需要创建最大堆，从最后一个的父节点开始
		int startIndex = getParentIndex(arrays.length - 1);
		//从尾端开始创建最大堆，每次都是正确的堆
		for(int i=startIndex; i>=0; --i) {
			maxHeapify(arrays, arrays.length, i);
		}
		System.out.println("堆:" + Arrays.toString(arrays));
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: maxHeapify   
	 * @Description: heapSize需要创建最大堆的大小，一般在sort的时候用到，因为最大值放在末尾，末尾就不再归入最大堆了
	 * index当前需要创建最大堆的位置
	 */
	public static void maxHeapify(final int[] arrays, int heapSize, int index) {
		// 当前点与左右子节点比较
		int left = getChildLeftIndex(index);
		int right = getChildRightIndex(index);
		int largest = index;
		if(left < heapSize && arrays[largest] < arrays[left]) {
			largest = left;
		}
		if(right < heapSize && arrays[largest] < arrays[right]) {
			largest = right;
		}
		//得到最大值后可能需要交换，如果交换了，其子节点可能就不是最大堆了，需要重新调整
		if(largest != index) {
			int temp = arrays[largest];
			arrays[largest] = arrays[index];
			arrays[index] = temp;
			maxHeapify(arrays, heapSize, largest);
		}
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: heapSort   
	 * @Description: 堆排序
	 * 排序，最大值放在末尾，arrays虽然是最大堆，在排序后就成了递增的
	 */
	public static void heapSort(final int[] arrays) {
		//末尾与头交换，交换后调整最大堆
		for(int i=arrays.length-1; i>=0; i--) {
			int temp = arrays[0];
			arrays[0] = arrays[i];
			arrays[i] = temp;
			maxHeapify(arrays, i, 0);
		}
		System.out.println("堆排序:" + Arrays.toString(arrays));
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: getParentIndex   
	 * @Description: 父节点位置   
	 */
	private static int getParentIndex(int current) {
		return (current - 1) >> 1;
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: getChildLeftIndex   
	 * @Description: 左子节点position   2 * i + 1
	 */
	private static int getChildLeftIndex(int current) {
		return (current << 1) + 1;
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: getChildRightIndex   
	 * @Description: 右子节点position   2 * i + 2
	 */
	private static int getChildRightIndex(int current) {
		return (current << 1) + 2;
	}
	
}
