package com.yn.utils;

import java.security.InvalidParameterException;
import java.util.Arrays;

/**
 * Copyright (C), nanyang205380@sohu-inc.com.
 * @ClassName: StringUtils   
 * @Description: 字符串相关算法
 * @author YangNan(杨楠)
 * @date 2015年2月9日 下午3:11:36 
 */
public class StringUtils {
	public static void main(String[] args) {
		String str = "abcdefghijklmnopqrstuvwxyz";
		System.out.println(Arrays.toString(subString(str, 25, 100)));
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: subString   
	 * @Description: 截取任意长度的字符串   
	 */
	public static String[] subString(String source, int start, int length) {
		
		if(source == null)
			return null;
		
		if(start >= source.length() || start < 0 || length < 0)
			throw new InvalidParameterException("out of bound");
		
		char[] characters = source.toCharArray();
		StringBuilder sb = new StringBuilder();
		int j = 0;
		for(int i = start; i<characters.length; ++i) {
			if(j++ == length) {
				sb.append(",");
				j = 1;
			}
			sb.append(characters[i]);
		}
		
		return sb.toString().split(",");
	}
}
