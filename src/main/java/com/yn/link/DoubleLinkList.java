package com.yn.link;

/**
 * Copyright (C), nanyang205380@sohu-inc.com.
 * @ClassName: DoubleLinkList   
 * @Description: 非循环的双向链表
 * @author YangNan(杨楠)
 * @date 2015年3月30日 下午5:28:18 
 */
public class DoubleLinkList {
	
	private Node head;
	private int size;
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: add   
	 * @Description: 采用尾部插入进行初始化链表   
	 */
	public void add(Node n) {
		
		if(n == null)
			return;
		
		if(head == null) {
			head = n;
			return;
		}
		
		Node p = head;
		while(p.next != null) {
			p = p.next;
		}
		p.next = n;
		n.prev = p;
		size++;
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: insert   
	 * @Description: 在index处插入节点   
	 */
	public void insert(int index, Node n) {

		Node p = head;
		if(index < 0 || index > size)
			return;
		if(head == null) {
			head = n;
			size++;
			return;
		}
		
		//链表头插入
		if(index == 0) {
			n.next = head;
			head.prev = n;
			head = n;
			size++;
			return;
		}
		
		p = getNode(index);
		if(p == null)
			return;
		
		//链表尾部插入
		if(p.next == null) {
			p.next = n;
			n.prev = p;
			size++;
			return;
		}
		
		p.prev.next = n;
		n.prev = p.prev;
		n.next = p;
		p.prev = n;
		size++;
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: delete   
	 * @Description: 删除某一个位置的节点   
	 */
	public void delete(int index) {
		if(index > size || index < 0)
			return;
		
		if(head == null)
			return;
		
		Node p = head;
		if(size == 1) {
			head = null;
			size--;
			return;
		}
		//删除头节点
		if(index == 0 && head.next != null) {
			head.next.prev = null;
			head = head.next;
			size--;
			return;
		}
		
		p = getNode(index);
		//删除尾部节点
		if(p.next == null) {
			p.prev.next = null;
			p.prev = null;
			size--;
			return;
		}
		
		p.prev.next = p.next;
		p.next.prev = p.prev;
		size--;
	}
	
	public Node getNode(int index) {
		if(index < 0 || index > size)
			return null;
		Node p = head; 
		while(index-- > 0 && p.next != null) {
			p = p.next;
		}
		
		return p;
	}
	
	public void print() {
		StringBuilder sb = new StringBuilder("正序:[");
		StringBuilder sb2 = new StringBuilder("倒序:[");
		Node p = head;
		Node tail = null;
		while(p != null) {
			sb.append(p.value).append(",");
			tail = p;
			p = p.next;
		}
		
		while(tail != null) {
			sb2.append(tail.value).append(",");
			tail = tail.prev;
		}
		
		if(head == null) {
			System.out.println(sb.append("]").toString());
			System.out.println(sb2.append("]").toString());
		} else {
			System.out.println(sb.deleteCharAt(sb.length() - 1).append("]").toString());
			System.out.println(sb2.deleteCharAt(sb2.length() - 1).append("]").toString());
		}
	}
	
	public int getSize() {
		return size;
	}
	
	public static void main(String[] args) {
		DoubleLinkList dl = new DoubleLinkList();
		for(int i=0;i<6;++i) {
			Node n = new Node(i);
//			dl.add(n);
			dl.insert(i, n);
		}
		dl.insert(0, new Node(-2));
		dl.print();
//		dl.delete(0);
//		dl.print();
	}
}
