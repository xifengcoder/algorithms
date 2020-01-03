package com.algorithms;

import java.util.Arrays;

/**
 * 最接近的三数之和.
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
 * 找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。
 * 假定每组输入只存在唯一答案。例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Created by Han on 2020/1/3
 */
public class ThreeSumClosest {

    /**
     * 思路:
     * 1. 现将nums按升序排列;
     * 2. 分别以nums[1..nums.length - 2]为中心, 即中心元素固定, 剩余的两项向两边扩散。
     * 如果sum比target大, 则左边项左移; 如果sum比target小, 则右边项右移。
     * <p>
     * 结论:
     * 执行用时 : 6 ms, 在所有 Java 提交中击败了85.78%的用户;
     * 内存消耗 : 36.7 MB, 在所有 Java 提交中击败了84.07%的用户;
     *
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = 0;
        int minDistance = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length - 1; i++) {
            System.out.println("i = " + i);
            int sum = 0;
            int left = i - 1;
            int right = i + 1;

            while (left >= 0 && left < i && right < nums.length && right > i) {
                sum = nums[left] + nums[i] + nums[right];
                System.out.println("sum = " + sum);
                if (sum == target) {
                    return sum;
                }

                System.out.println("distance = " + Math.abs(sum - target) + ", min = " + Math.abs(sum - target));
                if (Math.abs(sum - target) < minDistance) {
                    minDistance = Math.abs(sum - target);
                    result = sum;
                }

                if (sum > target) {
                    left--;
                } else {
                    right++;
                }
            }
        }

        return result;
    }

}
