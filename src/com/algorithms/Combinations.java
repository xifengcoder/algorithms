package com.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合
 * Created by xifeng.yang on 2019/11/10.
 */
public class Combinations {

    /**
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     * <p>
     * 示例:
     * <p>
     * 输入: n = 4, k = 2
     * 输出:
     * [
     * [2,4],
     * [3,4],
     * [2,3],
     * [1,2],
     * [1,3],
     * [1,4],
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/combinations
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> results = new ArrayList<>();
        if (n < 1 || k < 1 || n < k) {
            return results;
        }

        combine(1, n, k, new ArrayList<Integer>(), results);
        return results;
    }

    /**
     * 从[start, end]区间中选取k个元素，存放到temp中。
     */
    private void combine(int start, int end, int k, List<Integer> temp, List<List<Integer>> results) {
        if (k == 0) {
            List<Integer> result = new ArrayList<>(temp);
            results.add(result);
            return;
        }

        //这里使用剪枝进行了优化，只有当(end - start + 1) >= k时才会进入循环.
        for (int i = start; i <= (end - k + 1); i++) {
            temp.add(i);
            combine(i + 1, end, k - 1, temp, results);
            temp.remove(temp.size() - 1);
        }
    }
}
