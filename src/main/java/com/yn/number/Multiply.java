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
public class Multiply {
	public static void main(String[] args) {
//		String number1 = "96234623984325352868629323412456451245451215645";
//		String number2 = "934593826459982136492361231242342332342353223123498";
//		multiply(number1, number2);
//		BigDecimal b1 = new BigDecimal(new BigInteger(number1));
//		BigDecimal b2 = new BigDecimal(new BigInteger(number2));
//		System.out.println("api");
//		System.out.println("大数相乘结果:" + b1.multiply(b2));
		long start = System.currentTimeMillis();
		String r = factorial(10000);
		System.out.println("耗时:"+(System.currentTimeMillis() - start));
//		long a = dd(10);
		System.out.println("阶乘:"+r);
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
				c[i+j] += Integer.parseInt(String.valueOf(a[i])) * Integer.parseInt(String.valueOf(b[j])); 
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
	
	public static long dd(int number) {
		if(number == 1) return 1;
		return number * dd(number-1);
	}
	
}
