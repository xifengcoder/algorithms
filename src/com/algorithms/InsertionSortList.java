package com.algorithms;

public class InsertionSortList {
    public static void main(String[] args) {
        InsertionSortList o = new InsertionSortList();
        ListNode n1 = new ListNode(9);
        ListNode n2 = new ListNode(5);
        ListNode n3 = new ListNode(2);
        ListNode n4 = new ListNode(7);
        ListNode n5 = new ListNode(4);
        ListNode n6 = new ListNode(8);
        ListNode n7 = new ListNode(1);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        ListNode result = o.insertionSortList(n1);
        while (result != null) {
            System.out.println("val: " + result.val);
            result = result.next;
        }
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        dummyHead.next = head;

        ListNode cur = head;
        ListNode pre = dummyHead;

        while (cur != null) {
            if (cur.val < pre.val) {
                ListNode insertPos = dummyHead.next;
                ListNode insertPrev = dummyHead;

                //从队列首元素开始查找cur应该待的位置。
                while (insertPos != pre && cur.val > insertPos.val) {
                    insertPrev = insertPos;
                    insertPos = insertPos.next;
                }

                //cur位于insertPos的前面, 也就是将cur插入insertPrev和insertPos之间.
                pre.next = cur.next;
                cur.next = insertPos;
                insertPrev.next = cur;
                cur = pre.next;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }


        return dummyHead.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
