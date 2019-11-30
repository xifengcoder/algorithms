package com.algorithms.dynamicprogramming;

/**
 * 最长递增子序列问题.
 * Created by xifeng.yang on 2019/11/30
 */
public class LIS {

    public static void main(String[] args) {
        LIS lis = new LIS();
        int[] A = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        System.out.println(lis.lengthOfLIS(A));
    }

    /**
     * 分别求出以num[i](0<=i<len-1)结尾的最长递增子序列, 存储在数组中.
     * 然后遍历该数组, 取最大值.
     *
     * @param nums
     * @return
     */
    private int lengthOfLIS(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int result = 0;
        int[] lookup = new int[nums.length]; //以nums[i]结尾的最长递增子序列的最大值.
        lookup[0] = 1;

        for (int k = 1; k < nums.length; k++) {

            int maxValue = 1;
            for (int i = 0; i < k; i++) {
                if (nums[k] > nums[i]) {
                    maxValue = Integer.max(maxValue, lookup[i] + 1);
                }

                lookup[i] = maxValue;

            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (lookup[i] > result) {
                result = lookup[i];
            }
        }
        return result;
    }
}
