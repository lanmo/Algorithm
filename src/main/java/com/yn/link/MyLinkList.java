package com.yn.link;


/**
 * Copyright (C), nanyang205380@sohu-inc.com.
 * @ClassName: MyLinkList   
 * @Description: 链表
 * @author YangNan(杨楠)
 * @date 2015年3月19日 下午3:08:05 
 */
public class MyLinkList {
	private Node head;
	private int size;
	
	//添加元素
	public void add(Node node) {
		if(head == null) {
			head = node;
		} else {
			Node n = head;
			while(n.next != null) {
				n = n.next;
			}
			n.next = node;
		}
		size++;
	}
	
	public Node getNode(Node node) {
		if(head == null || node == null)
			return null;
		
		Node n = head;
		while(n != null) {
			if(n.value == node.value) {
				return n;
			}
			n = n.next;
		}
		return null;
	}
	
	//获取节点某个位置
	public Node getNode(int index) {
		if(index > size || index < 0 || head == null)
			return null;
		
		Node n = head;
		while(index-- > 0) {
			n = n.next;
		}
		return n;
	}
	
	//链表反转
	public void reverse() {
		if(head == null)
			return;
		
		Node n = null, newHead = null;
		while(head != null) {
			n = newHead;
			newHead = head;
			head = head.next;
			newHead.next = n;
		} 
		head = newHead;
	}
	
	//打印内容
	public void print() {
		if(head == null)
			System.out.println("list is null");
		
		Node n = head;
		StringBuilder sb = new StringBuilder("[");
		while(n != null) {
			sb.append(n.value).append(",");
			n = n.next;
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		System.out.println(sb.toString());
	}
	
	public int size() {
		return size;
	}
	
	public static void main(String[] args) {
		
		MyLinkList l = new MyLinkList();
		for(int i=0; i< 5; ++i) {
			Node n = new Node(i);
			l.add(n);
		}
		l.print();
		
		l.reverse();
		l.print();
		
	}
}
