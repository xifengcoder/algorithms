package com.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合综合
 * Created by xifeng.yang on 2019/11/09.
 */
public class CombinationSum {

    public static void main(String[] args) {
        CombinationSum permutation = new CombinationSum();
    }

    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * <p>
     * candidates 中的数字可以无限制重复被选取。
     * <p>
     * 说明：
     * <p>
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1:
     * <p>
     * 输入: candidates = [2,3,6,7], target = 7,
     * 所求解集为:
     * [
     * [7],
     * [2,2,3]
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/combination-sum
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> results = new ArrayList<>();
        combinationSum(0, target, candidates, new ArrayList<Integer>(), results);
        return results;
    }


    private void combinationSum(int start, int target, int[] nums, List<Integer> temp, List<List<Integer>> results) {
        for (int i = start; i < nums.length; i++) {
            if (target < nums[i]) {
                break;
            }
            if (target == nums[i]) {
                List<Integer> result = new ArrayList<>(temp);
                result.add(nums[i]);
                results.add(result);
            } else {
                temp.add((Integer) nums[i]);
                combinationSum(i, target - nums[i], nums, temp, results);
                temp.remove((Integer) nums[i]);
            }
        }

    }
}
