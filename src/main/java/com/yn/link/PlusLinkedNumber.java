package com.yn.link;

/**
 * Created by yangnan on 2017/5/17.
 */
public class PlusLinkedNumber {

    private Node head;
    private int size;

    private void print(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    /**
     * 链表转置
     *
     * @param head
     * @return
     */
    public Node reverseNode(Node head) {

        if (head == null) {
            throw new NullPointerException("'head' is null");
        }

        Node newHead = null;
        Node n = null;
        while (head != null) {
           n = newHead;
            newHead = head;
            head = head.next;
            newHead.next = n;
        }

        return newHead;
    }

    private void add(Node node) {
        if (node == null) {
            return;
        }

        size++;
        if (head == null) {
            head = node;
            return;
        }

        Node n = head;
        while (n.next != null) {
            n = n.next;
        }

        n.next = node;
    }

    private Node add(Node newHead, Node node) {
        if (node == null) {
            return null;
        }

        if (newHead == null) {
            return node;
        }

        Node n = newHead;
        while (n.next != null) {
            n = n.next;
        }

        n.next = node;
        return newHead;
    }

    private Node addLast(Node newHead, Node node) {
        if (node == null) {
            return newHead;
        }

        if (newHead == null) {
            return node;
        }

        node.next = newHead;

        return node;
    }


    public Node getHead() {
        return head;
    }

    public Node plus(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        Node n1 = reverseNode(head1);
        Node n2 = reverseNode(head2);

        Node newHead = null;
        int carry = -1;
        while (n1 != null || n2 != null) {
            int v1 = n1 == null ? 0 : n1.value;
            int v2 = n2 == null ? 0 : n2.value;
            int c = carry == -1 ? 0 : carry;

            int v = v1 + v2 + c;
            if (v > 9) {
                //有进位
                carry = v / 10;
            }

            Node next = new Node(v % 10);
            newHead = addLast(newHead, next);
            n1 = n1 == null ? null : n1.next;
            n2 = n2 == null ? null : n2.next;
        }

        if (carry >0 ) {
            Node next = new Node(carry);
            newHead = addLast(newHead, next);
        }

        return newHead;
    }

    /**
     * 不需要转置,但是两个链表的长度必须相等
     *
     * @param head1
     * @param head2
     * @return
     */
    public Node plusNoReverse(Node head1, Node head2) {

        Node current = null, pre = null, newHead = null;
        boolean first = true;
        Node n1 = head1, n2 = head2;
        while (n1 != null) {
            int v = n1.value + n2.value;
            if (first && v > 9) {
                newHead = new Node(v / 10);
                newHead.next = new Node(v % 10);
                current = newHead.next;
                pre = newHead.next;
            } else {
                if (v > 9) {
                    pre.value++;
                    current.next = new Node(v % 10);
                    while (pre != current) {
                        pre.next.value = 0;
                        pre = pre.next;
                    }
                    //重置
                    current = current.next;
                    pre = current;
                 } else {
                    if (current == null) {
                        newHead = new Node(v);
                        current = newHead;
                        pre = newHead;
                    } else {
                        current.next = new Node(v);
                        if (v < 9) {
                            pre = current.next;
                        }
                        current = current.next;
                    }
                }
            }

            n1 = n1.next;
            n2 = n2.next;
            first = false;
        }

        return newHead;
    }

    public static void main(String[] args) {

        PlusLinkedNumber plusLinkedNumber = new PlusLinkedNumber();
        PlusLinkedNumber plusLinkedNumber2 = new PlusLinkedNumber();

        PlusLinkedNumber plusLinkedNumber3 = new PlusLinkedNumber();
        PlusLinkedNumber plusLinkedNumber4 = new PlusLinkedNumber();

        int[] l1 = {9,9,9,8};
        int[] l2 = {1,1,1,7};

        for (int i=0 ;i<l1.length; i++) {
            plusLinkedNumber.add(new Node(l1[i]));
            plusLinkedNumber3.add(new Node(l1[i]));
        }

        for (int i=0 ;i<l2.length; i++) {
            plusLinkedNumber2.add(new Node(l2[i]));
            plusLinkedNumber4.add(new Node(l2[i]));
        }

        Node newH = plusLinkedNumber.plus(plusLinkedNumber.getHead(), plusLinkedNumber2.getHead());
        System.out.println("相加");
        plusLinkedNumber.print(newH);

        Node h = plusLinkedNumber3.plusNoReverse(plusLinkedNumber3.getHead(), plusLinkedNumber4.getHead());
        plusLinkedNumber3.print(h);


    }

    static class Node {
        public int value;

        public Node(int value) {
            this.value = value;
        }

        public Node next;
    }
}
