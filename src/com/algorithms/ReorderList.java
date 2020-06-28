package com.algorithms;

import java.util.ArrayList;

/**
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1:
 * <p>
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 * <p>
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorder-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReOrderList {
    public static void main(String[] args) {
        ReOrderList o = new ReOrderList();

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        o.reorderList2(n1);

        while (n1 != null) {
            System.out.println("node: " + n1.val);
            n1 = n1.next;
        }
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ArrayList<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }

        node = head;
        for (int i = 0, j = list.size() - 1; i <= j; i++, j--) {
            if (i < j) {
                node.next = list.get(i);
                node = node.next;
                node.next = list.get(j);
            } else {
                node.next = list.get(i);
            }
            node = node.next;
        }

        node.next = null;
    }

    public void reorderList2(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode l1 = head; //slow
        ListNode l2 = head; //fast
        ListNode prev = l1;
        while (l2 != null && l2.next != null) {
            l2 = l2.next.next;
            prev = l1;
            l1 = l1.next;
        }

        prev.next = null;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = l1;

        ListNode cur = l1.next;
        l1.next = null;
        ListNode nex;
        while (cur != null) {
            nex = cur.next;
            cur.next = dummyHead.next;
            dummyHead.next = cur;
            cur = nex;
        }

        l1 = head;
        l2 = dummyHead.next;

        ListNode nex1;
        ListNode nex2;
        while (l1.next != null && l2.next != null) {
            nex1 = l1.next;
            nex2 = l2.next;
            l1.next = l2;
            l2.next = nex1;
            l1 = nex1;
            l2 = nex2;
        }

        if (l1.next == null) {
            l1.next = l2;
        }
    }


    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
