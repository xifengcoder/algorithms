package com.algorithms;

/**
 * 经典问题: 排序链表
 * 在 O(nlogn) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 * <p>
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * <p>
 * Created by xifeng.yang on 2020/1/31
 */
public class SortedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(5);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(4);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;

        SortedList o = new SortedList();
        ListNode newHead = o.sortList(head);

    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int item) {
            val = item;
        }
    }

    /**
     * 解法一: 交换结点元素
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            while (next != null) {
                if (next.val < cur.val) {
                    int temp = next.val;
                    next.val = cur.val;
                    cur.val = temp;
                }
                next = next.next;
            }

            cur = cur.next;
        }
        return head;
    }

    /**
     * 执行用时: 321 ms, 在所有 Java 提交中击败了13.48%的用户;
     * 内存消耗: 40.6 MB, 在所有 Java 提交中击败了25.11%的用户.
     *
     * @param head
     * @return
     */
    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode cur = head.next;
        ListNode pre = head;

        while (cur != null) {
            if (cur.val < pre.val) {
                pre.next = cur.next;

                ListNode pre2 = dummyHead;
                ListNode cur2 = dummyHead.next;
                while (cur.val > cur2.val) {
                    pre2 = cur2;
                    cur2 = cur2.next;
                }

                //把cur节点插入到pre1和cur1之间
                pre2.next = cur;
                cur.next = cur2;
                cur = pre.next;
            } else {
                //向后移动
                pre = cur;
                cur = cur.next;
            }
        }

        return dummyHead.next;
    }


    /**
     * 执行用时: 618 ms, 在所有 Java 提交中击败了11.02%的用户,
     * 内存消耗: 40.1 MB, 在所有 Java 提交中击败了60.43%的用户.
     */
    public ListNode sortList3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        return quickSort(dummyHead, null);
    }

    /**
     * 解法: 自顶向下的归并排序
     * 执行用时: 4 ms, 在所有 Java 提交中击败了81.28%的用户,
     * 内存消耗: 40.3 MB, 在所有 Java 提交中击败了45.29%的用户.
     *
     * @param head
     * @return
     */
    public ListNode sortList4(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode fast = head.next;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode mid = slow.next;
        slow.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(mid);

        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode newDummy = new ListNode(0);
        ListNode tail = newDummy;

        while (left != null && right != null) {
            if (left.val <= right.val) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }

            tail = tail.next;
        }

        tail.next = left != null ? left : right;
        return newDummy.next;
    }

    public ListNode sortList5(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        for (int step = 1; step < len; step *= 2) {
            //从lo起所剩元素>step时才需要进行merge.
            for (int lo = 0; lo < len - step; lo += step * 2) {

            }

        }

        return dummyHead.next;
    }


    /**
     * 解法: 自底向上的归并排序.
     * 执行用时: 10 ms, 在所有 Java 提交中击败了27.36%的用户;
     * 内存消耗: 40.6 MB, 在所有 Java 提交中击败了25.93%的用户.
     *
     * @param head
     * @return
     */
    public ListNode sortListOld(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur, h1, h2, pre;
        cur = head;
        int length = 0;
        while (cur != null) {
            cur = cur.next;
            length++;
        }


        ListNode dummy = new ListNode(0);
        dummy.next = head;

        for (int step = 1; step < length; step *= 2) {
            pre = dummy;
            cur = dummy.next;

            while (cur != null) {
                int i = step;
                h1 = cur;
                while (i > 0 && cur != null) {
                    cur = cur.next;
                    i--;
                }

                if (i > 0 || cur == null) {
                    //从cur到尾部<=step个结点.
                    break;
                }

                i = step;
                h2 = cur;
                while (i > 0 && cur != null) {
                    cur = cur.next;
                    i--;
                }

                int c1 = step;

                //i>0表示从h2起到尾部<step个结点.
                int c2 = step - i;

                while (c1 > 0 && c2 > 0) {
                    if (h1.val < h2.val) {
                        pre.next = h1;
                        h1 = h1.next;
                        c1--;
                    } else {
                        pre.next = h2;
                        h2 = h2.next;
                        c2--;
                    }
                    pre = pre.next;
                }

                pre.next = c1 == 0 ? h2 : h1;

                //将pre推进到最后一个结点.
                while (c1 > 0 || c2 > 0) {
                    pre = pre.next;
                    c1--;
                    c2--;
                }

                pre.next = cur;
            }

        }

        return dummy.next;
    }

    private ListNode quickSort(ListNode head, ListNode end) {
        if (head == end || head.next == end || head.next.next == end) {
            return head;
        }

        ListNode newDummyHead = new ListNode(0);

        ListNode partition = head.next;
        ListNode pre = partition;
        ListNode cur = partition.next;

        ListNode tail = newDummyHead;

        while (cur != end) {
            if (cur.val < partition.val) {
                pre.next = cur.next;
                tail.next = cur;
                tail = tail.next;
                cur = pre.next;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }

        tail.next = partition;
        head.next = newDummyHead.next;

        quickSort(head, partition);
        quickSort(partition, end);
        return head.next;
    }
}
