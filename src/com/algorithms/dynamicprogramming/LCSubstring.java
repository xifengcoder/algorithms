package com.algorithms.dynamicprogramming;

/**
 * 最长公共子串(Longest Common Substring),
 * Created by xifeng.yang on 2019/11/25.
 */
class LCSubstring {

    public static void main(String[] args) {
        int[] A = new int[]{0, 1, 1, 1, 1};
        int[] B = new int[]{1, 0, 1, 0, 1};
        LCSubstring lcSubstring = new LCSubstring();
        int result = lcSubstring.findLength(A, B);
        System.out.println(result);
    }

    /**
     * 寻找最长公共子串的长度.
     */
    public int findLength(int[] A, int[] B) {
        return LCS(A, B);
    }

    /**
     * 寻找最长公共子串的长度, 思路: 针对i和j,分别计算最长公共后缀, 最大的最长公共后缀即为最长公共子串的长度.
     */
    private int LCS(int[] A, int[] B) {
        int[][] lookup = new int[A.length + 1][B.length + 1];

        int maxLen = 0;
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i - 1] == B[j - 1]) {
                    lookup[i][j] = lookup[i - 1][j - 1] + 1;
                }

                if (lookup[i][j] > maxLen) {
                    maxLen = lookup[i][j];
                }
            }
        }
        return maxLen;
    }
}