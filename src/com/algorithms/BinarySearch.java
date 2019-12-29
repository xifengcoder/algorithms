package com.algorithms;

/**
 * 二分查找算法
 * Created by xifeng.yang on 2019/12/29
 */
public class BinarySearch {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (high + low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int result = binarySearch.search(new int[]{-1, 0, 3, 5, 9, 12}, 9);
        System.out.println(result);
    }
}
