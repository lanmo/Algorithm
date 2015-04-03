package com.yn.number;
import java.util.Stack;

/**
 * Copyright (C), nanyang205380@sohu-inc.com.
 * @ClassName: Coin   
 * @Description: 题目：有三类硬币[7,5,2],随意给一个数，
 * 	问这个数能否用这三种硬币组成，并求出最优解（硬币数最少）
 * @author YangNan(杨楠)
 * @date 2015年4月3日 上午11:29:36 
 */
public class Coin {  
	
	private static int[] coins = {7,5,2};  
	private static Stack<Integer> coinsStack =new Stack<Integer>();//用于存储符合条件的硬币
	private static int min = Integer.MAX_VALUE;//最小值
	private static String coinStr = "";//用于存储最优解

    /**
     * 回溯算法递归实现
     * @param num
     */
    private static void getCoin(int num){
        for (int coin : coins){
        	//一直尝试用大面值的硬币，每次放到栈中，继续搜索符合条件的硬币，当发现超过给定的数之后，把最后入栈的硬币吐出来换小一点硬币的继续搜索，依次类推
            if (num - coin >=0 && (coinsStack.size()==0 || coin <= coinsStack.get(coinsStack.size()-1))){
            	coinsStack.push(coin);
                if (num - coin > 0){  
                	getCoin (num - coin);  
                } else {
                	String temp = "";//用于存储符合条件的硬币数
                	int count = 0;//硬币个数
                    for (Integer coinStack: coinsStack){ 
                    	temp += coinStack + " ";
                    	count++;
                    }
                    if(min > count) {//找到最优解
                    	min = count;
                    	coinStr = temp;
                    }
                }
                coinsStack.pop();  
            }
        }
        
    }
      
    public static void main(String[] args) { 
    	
        Coin.getCoin(20);
        System.out.println(coinStr);
        
    }  
}  