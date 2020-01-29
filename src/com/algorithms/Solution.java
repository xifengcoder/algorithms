package com.algorithms;

import java.util.Arrays;

/**
 * Created by xifeng.yang on 2020/1/28
 */
public class Solution {

    public static void main(String[] args) {

    }

    public int missingNumber(int[] nums) {
        int n = nums.length + 1;

        int sum = n * (n + 1) / 2;

        int left = sum;
        for (int i = 0; i < nums.length; i++) {
            left -= nums[i];
        }

        return left;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }

    /**
     * 找到nums1和nums2合并后从某个起始位置开始的第k个元素.
     *
     * @param nums1
     * @param start1 nums1的起始位置
     * @param nums2
     * @param start2 nums2的起始位置
     * @param k
     * @return
     */
    public int findKth(int[] nums1, int start1, int[] nums2, int start2, int k) {
        if (start1 >= nums1.length) {
            return nums2[start2 + k - 1];
        }

        if (start2 >= nums2.length) {
            return nums1[start1 + k - 1];
        }

        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        int midVal1 = (start1 + k / 2 - 1 < nums1.length) ? nums1[start1 + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (start2 + k / 2 - 1 < nums2.length) ? nums2[start2 + k / 2 - 1] : Integer.MAX_VALUE;

        if (midVal1 < midVal2) {
            return findKth(nums1, start1 + k / 2, nums2, start2, k - k / 2);
        } else {
            return findKth(nums1, start1, nums2, start2 + k / 2, k - k / 2);
        }
    }

    public String convert(String s, int numRows) {
        if (s == null) {
            return null;
        }

        if (numRows == 1) {
            return s;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < s.length(); j++) {
                if ((j % (numRows * 2 - 2) == i) || (j % (numRows * 2 - 2) == (numRows * 2 - 2) - i)) {
                    stringBuilder.append(s.charAt(j));
                }
            }
        }

        return stringBuilder.toString();
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode result = (l1.val <= l2.val) ? l1 : l2;

        ListNode cur = result;

        while (l1 != null && l2 != null) {

            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
                cur = cur.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
                cur = cur.next;
            }
        }

        if (l1 != null) {
            cur.next = l1;
        } else {
            cur.next = l2;
        }

        return result;
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        System.out.println("merge...");
        if (nums1 == null || nums2 == null) {
            return;
        }

        int len = m;
        for (int i = 0; i < n; i++) {
            System.out.println("i = " + i);
            int j = len - 1;
            while (j >= 0) {
                System.out.println("j = " + j);
                if (nums1[j] >= nums2[i]) {
                    nums1[j + 1] = nums1[j];
                    j--;
                } else {
                    break;
                }
            }
            nums1[j + 1] = nums2[i];
            len += 1;
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 1; i < m; i++) {
            pre = pre.next;
        }

        head = pre.next;
        ListNode current;

        //head始终指向第m个元素, prev始终指向head的前一个元素. 如果m=1,则prev指向dummy.
        //子链表[m..n]起始状态下, 队首为head, 前一个元素为prev.
        for (int i = m; i < n; i++) {
            //将head的后一个元素移动到队首, head相应后移一位.
            current = head.next;
            head.next = current.next;
            current.next = pre.next;
            pre.next = current;
        }

        return dummy.next;
    }

    public ListNode reverseBetween2(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;

        for (int i = 1; i < m; i++) {
            pre = pre.next;
        }

        head = pre.next;
        ListNode current = head;
        ListNode prev = null;

        for (int i = m; i <= n; i++) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        pre.next = prev;
        head.next = current;
        return dummy.next;
    }

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int[] lookup = new int[nums.length];
        lookup[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            int maxValue = 1;

            for (int k = 0; k < i; k++) {
                if (nums[i] == nums[k] + 1) {
                    maxValue = Integer.max(maxValue, lookup[k] + 1);
                }
            }

            lookup[i] = maxValue;
        }

        int max = 0;
        for (int i = 0; i < lookup.length; i++) {
            if (max < lookup[i]) {
                max = lookup[i];
            }
        }
        return max;

    }

    public static int myAtoi(String str) {
        if (str == null) {
            return 0;
        }

        String content = str.trim();
        if (content.length() == 0) {
            return 0;
        }
        int bol = 1;
        int ans = 0;
        char[] cdhr = content.toCharArray();
        int i = 0;
        if (cdhr[0] == '-') {
            bol = -1;
            i = i + 1;
        } else if (cdhr[0] == '+') {
            i = i + 1;
        }
        for (; i < content.length(); i++) {
            if (48 > content.charAt(i) || content.charAt(i) > 57) {
                break;
            }

            if (ans * bol > Integer.MAX_VALUE / 10 || ans * bol == Integer.MAX_VALUE / 10 && (cdhr[i] - 48) > 7) {
                return Integer.MAX_VALUE;
            }
            if (ans * bol < Integer.MIN_VALUE / 10 || ans * bol == Integer.MIN_VALUE / 10 && (cdhr[i] - 48) > 8) {
                return Integer.MIN_VALUE;
            }
            ans = ans * 10 + (cdhr[i] - 48);

        }
        ans = ans * bol;

        return ans;

    }
}
