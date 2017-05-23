package com.yn;

public class TwoLinkedPlus {
  
    /** 
     * @author yyp 
     * @createdate 2014-1-26 下午12:39:41 
     * @Description: (用一句话描述该方法做什么) 
     * @param args 
     *  
     */  
    public static void main(String[] args) {  
        Node node1 = new Node(9,new Node(9, new Node(9, new Node(9,null))));
        Node node2 = new Node(1,new Node(1, new Node(1, new Node(1,null))));
        printNode(node1);  
        printNode(node2);  
        Node node3 = twoLinkedPlus(node1, node2);  
        printNode(node3);  
    }  
    /** 
     * 两个单链表相加 
     * 主要算法是：在返回的相加后的单链表上，记录一个可以用于进位的节点，当需要 
     * 进位时，此节点的值自增。这个进位节点是当前求得节点值之前的第一个值小于9的节点 
     * 比如： 
     *   1 2 3 
     * + 4 5 6 
     * 当计算第二位的相加结果时（即 2 + 5）时，进位节点为结果链表的第一个节点，节点值为5 
     *   3 4 6 7 
     * + 2 5 3 4 
     * 当计算第四位数相加的结果时，进位数节点为结果链表的第一个节点，节点值为5 
     * @author yyp 
     * @createdate 2014-1-26 下午3:03:53 
     * @Description: (用一句话描述该方法做什么) 
     * @param first 
     * @param second 
     * @return 
     * 
     */  
    public static Node twoLinkedPlus(Node first, Node second) {  
        Node prePlusNode = null;  
        Node curPlusNode = null;  
        Node resultNode = null;  
        int num = 1;  
        while(first != null) {  
            int retValueTmp = first.value + second.value;  
            //如果第一位数要进位  
            if(retValueTmp >= 10 && num == 1){  
                resultNode = new Node(1, null);  
                resultNode.next  = new Node(retValueTmp - 10, null);  
                prePlusNode = resultNode.next;  
                curPlusNode = resultNode.next;  
            //不是第一位的其他位数进位  
            }else if(retValueTmp >= 10){  
                //当前用于进位的数自增  
                prePlusNode.value++;  
                curPlusNode.next = new Node(retValueTmp - 10, null);  
                //当需要累积进位时，将用于进位的数和当前操作数的值设为0  
                while(prePlusNode != curPlusNode){  
                    prePlusNode.next.value = 0;  
                    prePlusNode = prePlusNode.next;  
                }  
                //重新设置用于进位的数和当前操作的数
                prePlusNode = curPlusNode;
                curPlusNode = curPlusNode.next;  
            }else{  
                if(curPlusNode == null){  
                    //初始化  
                    resultNode = new Node(retValueTmp, null);  
                    curPlusNode = resultNode;  
                    prePlusNode = resultNode;  
                }else{  
                    curPlusNode.next = new Node(retValueTmp, null);  
                    if(retValueTmp < 9){  
                        prePlusNode =curPlusNode.next;  
                    }  
                    curPlusNode = curPlusNode.next;  
                }  
            }  
            first = first.next;  
            second = second.next;  
            num ++;  
        }  
        return resultNode;  
    }  
    /** 
     * 输出单链表代表的数 
     * @author yyp 
     * @createdate 2014-1-26 下午3:03:26 
     * @Description: (用一句话描述该方法做什么) 
     * @param node 
     * 
     */  
    public static void printNode(Node node){  
        while(node != null){  
            System.out.print(node.value);  
            node = node.next;  
        }  
        System.out.println();  
    }  
  
}  
/** 
 * 节点类，限制value的值只能为0~9的数字 
 * @author yyp 
 * @createdate 2014-1-26 下午3:02:41 
 * @Description: TODO(用一句话描述该类做什么) 
 */  
class Node{  
    protected int value;  
    protected Node next;  
    public Node() {}  
    public Node(int value, Node next) {  
        if(value < 0 || value > 9){  
            throw new RuntimeException("parameter erroe!");  
        }  
        this.value = value;  
        this.next = next;  
    }  
}  