package com.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 排列问题
 * Created by xifeng.yang on 2019/11/04.
 */
public class Permutation {

    public static void main(String[] args) {
        Permutation permutation = new Permutation();
        int[] nums = new int[]{1, 3, 5, 4, 2};
        //permutation.nextPermutation(nums);
        List<List<Integer>> results = permutation.permute(nums);
        System.out.println(results);
    }

    /**
     * 给定一个没有重复数字的序列，返回其所有可能的全排列。
     * <p>
     * 示例:
     * <p>
     * 输入: [1,2,3]
     * 输出:
     * [
     * [1,2,3],
     * [1,3,2],
     * [2,1,3],
     * [2,3,1],
     * [3,1,2],
     * [3,2,1]
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/permutations
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        permute(nums, 0, results);
        return results;
    }


    /**
     * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     * <p>
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     * <p>
     * 必须原地修改，只允许使用额外常数空间。
     * <p>
     * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/next-permutation
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        int j;
        while (i > 0 && nums[i] <= nums[i - 1]) { //<=号表示可包含重复元素
            i--;
        }

        if (i == 0) {
            //最大的排列, 逆序输出.
            reverse(nums);
        } else if (i == nums.length - 1) {
            //最小的排列，交换后两个元素.
            swap(nums, i, i - 1);
        } else {
            //从[i, len -1]中选取比i - 1大的元素中的最小者, 与 i - 1交换, 然后[i, len - 1]递增排序.
            for (j = nums.length - 1; j >= i; j--) {
                if (nums[j] > nums[i - 1]) {
                    break;
                }
            }

            swap(nums, j, i - 1);
            Arrays.sort(nums, i, nums.length);
        }
    }

    private void permute(int[] nums, int start, List<List<Integer>> results) {
        if (start == nums.length - 1) {
            List<Integer> result = new ArrayList<>();
            for (int i : nums) {
                result.add(i);
            }
            results.add(result);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            swap(nums, i, start);
            permute(nums, start + 1, results);
            swap(nums, i, start);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    private void reverse(int[] nums) {
        int temp;
        for (int i = 0; i < nums.length / 2; i++) {
            temp = nums[i];
            nums[i] = nums[nums.length - i - 1];
            nums[nums.length - i - 1] = temp;
        }
    }
}
