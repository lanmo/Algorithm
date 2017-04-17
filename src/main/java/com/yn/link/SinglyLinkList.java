package com.yn.link;

/**
 * Copyright (C), nanyang205380@sohu-inc.com.
 * @ClassName: SinglyLinkList   
 * @Description: 单向链表
 * @author YangNan(杨楠)
 * @date 2015年3月19日 下午3:08:05 
 */
public class SinglyLinkList {
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
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: delete   
	 * @Description: 删除节点   
	 */
	public void delete(int index) {
		if(head == null || index < 0 || index >= size)
			return;
		
		//如果是删除头
		if(index == 0) {
			head = head.next;
			size--;
			return;
		}
		
		//找到待删除节点的前一个节点
		Node n = getNode(index - 1);
		if(n != null && n.next != null) {
			n.next = n.next.next;
			size--;
		}
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
	
	//获取某个位置的节点
	public Node getNode(int index) {
		if(index >= size || index < 0 || head == null)
			return head;
		
		Node n = head;
		while(index-- > 0 && n.next != null) {
			n = n.next;
		}
		return n;
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: reverse   
	 * @Description: 链表倒序   
	 */
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
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Description: 找到链表中间的那个节点,
	 * 算法思想:
	 * 设立两个指针，一个单步走，一个两步走，当大步指针到达链表尾部的时候，小步指针也正好位于链表中间位置
	 */
	public Node searchMiddle() {
		if(head == null || head.next == null)
			return head;
		
		Node p1, p2;
		p1 = p2 = head;
		while(p2.next != null && p2.next.next != null) {
			p1 = p1.next;
			p2 = p2.next.next;
		}
		return p1;
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Description: 合并有序链表
	 * 算法思想:
	 * 1.先判断链表是否为NULL
	 * 2.依次比较两个链表的头结点，把更小的节点放到新链表中去，继续遍历
	 */
	public Node mergeOrdered(Node head1, Node head2) {

		Node head = null;
		if(head1 == null)
			return head2;
		if(head2 == null)
			return head1;
		
		if(head1.value < head2.value) {
			head = head1;
			head.next = mergeOrdered(head1.next, head2);
		} else {
			head = head2;
			head.next = mergeOrdered(head1, head2.next);
		}
		
		return head;
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Description: 输入一个单向链表，输出该链表中倒数第k个节点
	 * 算法思想:
	 * 1.设置两个指针 p1、p2，首先 p1 和 p2 都指向 head，然后 p2 向前走 k 步，
	 * 2.这样 p1 和 p2 之间就间隔 k 个节点，最后 p1 和 p2 同时向前移动，直至 p2 走到链表末尾
	 * @return:    (参数说明)   
	 */
	public Node getKNode(int k) {
		
		if(k < 0)
			return null;

		Node p1 = head, p2 = head;
		while(k-- > 0 && p2 != null) {
			p2 = p2.next;
		}
		//大于链表的长度
		if(k > 0) 
			return null;
		
		while(p2 != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p1;
	}
	
	/**
	 * @author: YangNan(杨楠)
	 * @Description:输入一个单向链表，判断链表是否有环    
	 * 算法思想:
	 * 1.通过两个指针，分别从链表的头节点出发，一个每次向后移动一步，另一个移动两步，两个指针移动速度不一样，如果存在环，那么两个指针一定会在环里相遇
	 */
	public boolean hasCircle(Node head) {
		if(head == null)
			return false;
		
		Node p1 = head,p2 = head;
		while(p2 != null && p2.next != null) {
			p1 = p1.next;
			p2 = p2.next.next;
			if(p1 == p2)
				return true;
		}
		return false;
	}
	
	public Node findCircleNode(Node head) {
		if(head == null)
			return null;
		
		Node p1 = head,p2 = head;
		while(p2 != null && p2.next != null) {
			p1 = p1.next;
			p2 = p2.next.next;
			if(p1 == p2)
				return p2;
		}
		
		return null;
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: findLoopPort   
	 * @Description: 判断链表是否有环。如果链表存在环，如何找到环的入口点
	 * 算法思想:
	 * 1.按照 p2 每次两步，p1 每次一步的方式走，发现 p2 和 p1 重合，确定了单向链表有环路了。
	 * 2.让p2回到链表的头部，重新走，每次步长不是走2了，而是走1，那么当 p1 和 p2 再次相遇的时候，就是环路的入口了。
	 * why？：假定起点到环入口点的距离为 a，p1 和 p2 的相交点M与环入口点的距离为b，环路的周长为L，当 p1 和 p2 第一次相遇的时候，假定 p1 走了 n 步。那么有：
	 *		p1走的路径： a+b ＝ n；
	 *		p2走的路径： a+b+k*L = 2*n； p2 比 p1 多走了k圈环路，总路程是p1的2倍
	 * 根据上述公式可以得到 k*L=a+b=n显然，如果从相遇点M开始，p1 再走 n 步的话，还可以再回到相遇点，同时p2从头开始走的话，经过n步，也会达到相遇点M。
	 *	显然在这个步骤当中 p1 和 p2 只有前 a 步走的路径不同，所以当 p1 和 p2 再次重合的时候，必然是在链表的环路入口点上。
	 */
	public Node findLoopPort(Node head) {

		if(head == null)
			return head;
		
		Node p1 = head,p2 = head;
		while(p2 != null && p2.next != null) {
			p1 = p1.next;
			p2 = p2.next.next;
			if(p1 == p2)
				break;
		}
		
		//不存在环
		if(p1 != p2) 
			return null;
		
		p2 = head;
		while(p1 != p2) {
			p1 = p1.next;
			p2 = p2.next;
		}
		
		return p2;
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: isIntersect   
	 * @Description: 判断这两个链表是否相交。这里为了简化问题，我们假设两个链表均不带环
	 * 算法思想:
	 * 如果两个没有环的链表相交于某一节点，那么在这个节点之后的所有节点都是两个链表共有的”这个特点，我们可以知道，如果它们相交，则最后一个节点一定是共有的。
	 * 而我们很容易能得到链表的最后一个节点，所以这成了我们简化解法的一个主要突破口。那么，我们只要判断两个链表的尾指针是否相等。相等，则链表相交；
	 * 否则，链表不相交。
	 *所以，先遍历第一个链表，记住最后一个节点。
	 *然后遍历第二个链表，到最后一个节点时和第一个链表的最后一个节点做比较，如果相同，则相交，否则，不相交。
	 *这样我们就得到了一个时间复杂度，它为O((Length(h1) + Length(h2))，而且只用了一个额外的指针来存储最后一个节点。
	 *这个方法时间复杂度为线性O(N)，空间复杂度为O(1)
	 * */
	public boolean isIntersect(Node h1, Node h2) {
		if(h1 == null || h2 == null)
			return false;
		
		Node p1 = h1;
		while(p1.next != null) {
			p1 = p1.next;
		}
		
		Node p2 = h2;
		while(p2.next != null) {
			p2 = p2.next;
		}
		//判断尾节点是否相等
		return p1 == p2;
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: isIntersectWithLoop   
	 * @Description: 扩展:链表有环，如何判断相交
	 * 上面的问题都是针对链表无环的，那么如果现在，链表是有环的呢?上面的方法还同样有效么?
	 *   算法思想:
	 *   1.如果有环且两个链表相交，则两个链表都有共同一个环，即环上的任意一个节点都存在于两个链表上。
	 *   2.因此，就可以判断一链表上俩指针相遇的那个节点，在不在另一条链表上   
	 */
	public boolean isIntersectWithLoop(Node h1, Node h2) {
		if(h1 == null || h2 == null)
			return false;
		
		Node p1 = findCircleNode(h1), p2 = findCircleNode(h2);
		//判断链表带不带环，并保存环内节点
		if(p1 == null)
			return false;//不带环，异常退出
		
		if(p2 == null)
			return false;//不带环，异常退出
		
		Node n = p2.next;
		//走了一圈
		while(n != p2) {
			if(n == p1)
				return true;
			
			n = n.next;
		}
		return false;
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: findIntersectNode   
	 * @Description: 如果两个无环单链表相交，怎么求出他们相交的第一个节点呢
	 * 算法思想:
	 * 1.采用对齐的思想。计算两个链表的长度 L1 , L2，分别用两个指针 p1 , p2 指向两个链表的头，
	 * 2.然后将较长链表的 p1（假设为 p1）向后移动L2 - L1个节点，然后再同时向后移动p1 , p2，直到 p1 = p2。相遇的点就是相交的第一个节点   
	 */
	public Node findIntersectNode(Node h1, Node h2) {
		int len1 = listLength(h1);
		int len2 = listLength(h2);
		if(len1 > len2) {
			for(int i=0; i<len1 - len2; ++i) {
				h1 = h1.next;
			}
		} else {
			for(int i=0; i<len2 - len1; ++i) {
				h2 = h2.next;
			}
		}
		
		while(h1 != null) {
			if(h1 == h2)
				return h1;
			h1 = h1.next;
			h2 = h2.next;
		}
		
		return null;
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: deleteRandomNode   
	 * @Description: 给定链表的头指针和一个节点指针，在O(1)时间删除该节点
	 * 算法思想:
	 * 用下一个节点数据覆盖要删除的节点，然后删除下一个节点
	 */
	public void deleteRandomNode(Node cur) {
		if(cur == null)
			return;
		
		Node pNext = cur.next;
		if(pNext != null) {
			cur.value = pNext.value;
			cur.next = pNext.next;
		}
	}
	
	/**
	 * @author: YangNan(杨楠)  
	 * @Title: listLength   
	 * @Description: 求链表的长度   
	 */
	public int listLength(Node head) {
		int count = 0;
		while(head != null) {
			head = head.next;
			count ++;
		}
		return count;
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
		
		SinglyLinkList first = new SinglyLinkList();
		SinglyLinkList second = new  SinglyLinkList();
		for(int i=0; i< 6; ++i) {
			Node n = new Node(i);
			first.add(n);
		}
		
		for(int i=6; i<10; ++i) {
			Node n = new Node(i);
			second.add(n);
		}
		
//		first.print();
//		first.reverse();
//		first.print();
		
//		Node n = first.searchMiddle();
//		System.out.println("中间:" + n.value);
		
//		System.out.println("first:");
//		first.print();
//		System.out.println("second:");
//		second.print();
//		Node newHead = first.mergeOrdered(first.head, second.head);
//		System.out.println("结果:");
//		print(newHead);
		
//		first.delete(3);
//		first.print();
//		Node n = first.getKNode(4);
//		System.out.println(n.value);
		System.out.println(first.listLength(first.getNode(0)));
	}
}