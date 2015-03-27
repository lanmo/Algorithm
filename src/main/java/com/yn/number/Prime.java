package com.yn.number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), nanyang205380@sohu-inc.com.
 * 
 * @ClassName: Prime
 * @Description: 质数判断 质数定义：只能被1或者自身整除的自然数（不包括1），称为质数。
 * @author YangNan(杨楠)
 * @date 2015年3月27日 上午9:58:53
 */
public class Prime {
	public static void main(String[] args) {
		int start = 1000;
		int end = 9999;
		List<Integer> prime = new ArrayList<Integer>();
		List<Integer> prime2 = new ArrayList<Integer>();
		for(int i=start; i<=end; ++i) {
			if(isPrime(i))
				prime.add(i);
			if(isPrime2(i))
				prime2.add(i);
		}
		
		System.out.println("结果1:" + Arrays.toString(prime.toArray()));
		System.out.println("结果2:" + Arrays.toString(prime2.toArray()));
		System.out.println("长度:"+prime.size());
	}

	/**
	 * @author: YangNan(杨楠)
	 * @Title: isPrime
	 * @Description: 利用它的定义可以循环判断该数除以比它小的每个自然数（大于1），如果有能被它整除的，则它就不是质数.
	 */
	public static boolean isPrime(int n) {
		for (int i = 2; i < n; ++i) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	/**
	 * @author: YangNan(杨楠)
	 * @Title: isPrime
	 * @Description: 
	 *               利用一个定理——如果一个数是合数，那么它的最小质因数肯定小于等于他的平方根。例如：50，最小质因数是2，2<50的开根号
	 *               再比如:15，最小质因数是3，3<15的开根号,合数是与质数相对应的自然数。一个大于1的自然数如果它不是合数，则它是质数。
	 *               　　上面的定理是说，如果一个数能被它的最小质因数整除的话，那它肯定是合数，即不是质数。所以判断一个数是否是质数，
	 *               只需判断它是否能被小于它开跟后后的所有数整除，这样做的运算就会少了很多，因此效率也高了很多。
	 */
	public static boolean isPrime2(int n) {
		int sqrt = (int)Math.sqrt(n);
		for (int i = 2; i <= sqrt; ++i) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
}
