package com.algorithms;

/**
 * Created by Han on 2020/1/11
 */
public class RemoveDuplicates {

    public static void main(String[] args) {

        RemoveDuplicates object = new RemoveDuplicates();

    }

    /**
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     * <p>
     * 示例1:
     * 给定 nums = [1,1,1,2,2,3],
     * <p>
     * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3。
     * 你不需要考虑数组中超出新长度后面的元素。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        if (nums.length == 1) {
            return 1;
        }

        boolean enough = false;
        for (int i = 1; i < len; i++) {
            if (!enough) {
                enough = nums[i] == nums[i - 1];
            } else {
                if (nums[i] != nums[i - 1]) {
                    enough = false;
                } else {
                    //从k开始，找到i后面所有等于nums[i]的元素, 全部删除.
                    int k = i + 1;
                    while (k < len) {
                        if (nums[k] != nums[i]) {
                            break;
                        } else {
                            k++;
                        }
                    }

                    //将[i, k)之间的元素删除, [k, len)之间的元素复制到[k...).
                    if (k < len) {
                        System.arraycopy(nums, k, nums, i, len - k);
                        enough = nums[i] == nums[i - 1];
                    }
                    len -= (k - i);
                }
            }
        }
        return len;
    }

    /**
     * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->2->3->3->4->4->5
     * 输出: 1->2->5
     * 示例 2:
     * <p>
     * 输入: 1->1->1->2->3
     * 输出: 2->3
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     **/
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode prev = dummyHead;

        int duplicateNum = 1;

        ListNode current = head;
        while (current.next != null) {
            if (current.val == current.next.val) {
                duplicateNum++;
                current = current.next;
            } else {
                if (duplicateNum > 1) {
                     prev.next = current.next; //删除
                     current = current.next;
                     duplicateNum = 1;
                } else {
                    prev = current;
                    current = current.next;
                }
            }
        }

        if(duplicateNum > 1) {
            prev.next = null;
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
