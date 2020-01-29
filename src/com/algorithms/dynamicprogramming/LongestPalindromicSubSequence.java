package com.algorithms.dynamicprogramming;

import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.Map;

/**
 * 经典问题: 最长回文子序列
 * Created by xifeng.yang on 2020/1/29
 */
public class LongestPalindromicSubSequence {

    public static void main(String[] args) {

    }

    /**
     * 解法一: 递归法, 最差情况下时间复杂度O(2^n)
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubSeq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        return longestPalindrome(s, 0, s.length() - 1);
    }

    private int longestPalindrome(String s, int i, int j) {

        if (i > j) {
            return 0;
        }

        if (i == j) {
            return 1;
        }

        if (s.charAt(i) == s.charAt(j)) {
            return longestPalindrome(s, i + 1, j - 1) + 2;
        }


        return Integer.max(longestPalindrome(s, i, j - 1),
                longestPalindrome(s, i + 1, j));

    }

    /**
     * 解法二: 动态规划法
     * 执行用时:484 ms, 在所有 Java 提交中击败了5.05%.
     *
     * <p>
     * 时间复杂度O(n^2), 空间复杂度O(n^2).
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubSeqDP(String s) {
        HashMap<String, Integer> lookup = new HashMap<>();
        return longestPalindromeSubSeqDP(s, 0, s.length() - 1, lookup);
    }

    private int longestPalindromeSubSeqDP(String s, int i, int j, HashMap<String, Integer> lookup) {
        if (i > j) {
            return 0;
        }

        if (i == j) {
            return 1;
        }

        String key = i + "|" + j;
        if (!lookup.containsKey(key)) {
            if (s.charAt(i) == s.charAt(j)) {
                lookup.put(key, longestPalindromeSubSeqDP(s, i + 1, j - 1, lookup) + 2);
            } else {
                lookup.put(key, Integer.max(longestPalindromeSubSeqDP(s, i, j - 1, lookup),
                        longestPalindromeSubSeqDP(s, i + 1, j, lookup)));
            }

        }

        return lookup.get(key);
    }

    /**
     * 解法三: 动态规划
     * 执行用时 : 28 ms, 在所有 Java 提交中击败了41.03%的用户;
     * 内存消耗 : 47.8 MB, 在所有 Java 提交中击败了41.27%的用户.
     * <p>
     * 注意点:
     * 计算T[i][j]时需要依赖T[i+1][j-1],T[i+1][j],T[i][j-1], 因此i应该从大到小递减；j应该从小到大递增。
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubSeqDP2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[][] T = new int[s.length()][s.length()];

        for (int j = 0; j < s.length(); j++) {
            T[j][j] = 1;
            for (int i = j - 1; i >= 0; i--) {
                if (s.charAt(i) == s.charAt(j)) {
                    T[i][j] = T[i + 1][j - 1] + 2;
                } else if (s.charAt(i) != s.charAt(j)) {
                    T[i][j] = Integer.max(T[i + 1][j],
                            T[i][j - 1]);
                }
            }
        }

        return T[0][s.length() - 1];
    }


    /**
     * 解法三: 动态规划
     * 执行用时: 31ms, 在所有 Java 提交中击败了32.55%的用户.
     * 内存消耗: 47.3 MB, 在所有 Java 提交中击败了91.78%的用户.
     * <p>
     * 思路:
     * 先将字符串s翻转, 记为reverse(s).
     * 则s和reverse(s)最长公共子序列即为最长回文子序列.
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubSeqDP3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        String reversed = new StringBuilder(s).reverse().toString();
        int[][] T = new int[s.length() + 1][s.length() + 1];

        return lCSLength(s, reversed, s.length(), T);
    }

    /**
     * 求解X和Y的前n个字符的最长公共子序列.
     *
     * @param X
     * @param Y
     * @param n
     * @param T
     * @return
     */
    private int lCSLength(String X, String Y, int n, int[][] T) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    T[i][j] = T[i - 1][j - 1] + 1;
                } else {
                    T[i][j] = Integer.max(T[i][j - 1], T[i - 1][j]);
                }
            }
        }

        return T[n][n];
    }
}
