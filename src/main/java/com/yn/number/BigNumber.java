package com.yn.number;

import java.math.BigDecimal;
import java.math.BigInteger;


/**
 * Copyright (C), nanyang205380@sohu-inc.com.
 * @ClassName: Multiply   
 * @Description: 大数
 * @author YangNan(杨楠)
 * @date 2015年2月9日 下午5:19:15 
 */
public class BigNumber {
	public static void main(String[] args) {
		String number1 = "96234623984325352868629323412456451245451215645";
		String number2 = "934593826459982136492361231242342332342353223123498";
//		multiply(number1, number2);
		BigDecimal b1 = new BigDecimal(new BigInteger(number1));
		BigDecimal b2 = new BigDecimal(new BigInteger(number2));
		System.out.println("api");
		System.out.println("大数相加:" + b1.add(b2));
//		long start = System.currentTimeMillis();
//		String r = factorial(100);
//		System.out.println("耗时:"+(System.currentTimeMillis() - start));
////		long a = fun(10);
//		System.out.println("阶乘:"+r);
		
//		long r1 = power(2, 20);
//		System.out.println("幂运算:" + r1);
//		powerBigNumber(2, 64);
		
		String rr = addBigNumber(number1, number2);
		System.out.println("大数相加:"+rr);
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: multiply   
	 * @Description: 大数相乘   
	 */
	public static String multiply(String number1, String number2) {
		
		//翻转字符串,便于操作
		StringBuilder sb = new StringBuilder(number1);
		StringBuilder sb2 = new StringBuilder(number2);
		char[] a = sb.reverse().toString().toCharArray();
		char[] b = sb2.reverse().toString().toCharArray();
		
		// 两数乘积位数不会超过乘数位数和+ 3位
		int[] c = new int[a.length + b.length + 3];
		//逐位相乘
		for(int i = 0; i<a.length; ++i) {
			for(int j=0; j<b.length; ++j) {
				c[i+j] += (a[i] - '0') * (b[j] - '0'); 
			}
		}
		
		//计算进位
		for(int i=0; i<c.length; ++i) {
			int carry = c[i] / 10;
			c[i] = c[i] % 10;
			if(carry > 0) {
				c[i+1] += carry;
			}
		}
		
		int m=0;
		//找到最高位
		for(m=c.length-1; m>=0;) {
			if(c[m] > 0)
				break;
			m--;
		}
		
		StringBuilder r = new StringBuilder();
		for(int i = 0; i<=m; ++i) {
			r.append(c[m -i]);
		}
		
//		System.out.println("大数相乘结果:" + r.toString());
		
		return r.toString();
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: factorial   
	 * @Description: 大数阶乘 10000   
	 * @throws:
	 */
	public static String factorial(int number) {

		int a = number;
		int b= number-1;
		int d = number -2;
		String c = multiply(String.valueOf(a), String.valueOf(b));
		while(d > 1) {
			c = multiply(c, String.valueOf(d));
			d--;
		}
		
		return c;
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: fun   
	 * @Description: 小数的阶乘   
	 */
	public static long fun(int number) {
		if(number == 1) return 1;
		return number * fun(number-1);
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: power   
	 * @Description: 小数幂运算   
	 */
	public static long power(int low, int index) {
		if(index == 0) return 1;
		
		return low * power(low, index-1);
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: powerBigNumber   
	 * @Description: 大数幂运算   
	 */
	public static String powerBigNumber(int low, int index) {
		
		String c = String.valueOf(low);
		for(int i=1; i<index; ++i) {
			c = multiply(c, String.valueOf(low));
		}
		System.out.println("大数幂:" + c);
		return c;
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: addBigNumber   
	 * @Description: 大数相加   
	 */
	public static String addBigNumber(String number1, String number2) {
		
		//字段串翻转
		StringBuilder sb1 = new StringBuilder(number1);
		StringBuilder sb2 = new StringBuilder(number2);
		
		char[] c1 = sb1.reverse().toString().toCharArray();
		char[] c2 = sb2.reverse().toString().toCharArray();
		int len1 = c1.length;
		int len2 = c2.length;
		
		// 两数相加位数不会超过最大的位数 + 1
		int len = len1 > len2 ? len1+1 : len2+1;
		int[] r = new int[len];
		
		//逐位相加
		for(int i=0; i< len; ++i) {
			int cr1 = i >= len1 ? 0 : c1[i] - '0';
			int cr2 = i >= len2 ? 0 : c2[i] - '0';
			r[i] = cr1 + cr2;
		}
		
		//计算进位
		for(int i=0; i<len; ++i) {
			int carry = r[i] / 10;
			r[i] = r[i] % 10;
			if(carry > 0)
				r[i+1] += carry;
		}
		
		//找出第一个不为0的最高为
		int m = 0;
		for(m=r.length-1; m>=0;) {
			if(r[m] > 0)
				break;
			m--;
		}
		
		StringBuilder s = new StringBuilder();
		for(int i=0; i<=m; ++i) {
			s.append(r[m-i]);
		}
		
		return s.toString();
	}
}
