package com.algorithms.dynamicprogramming;

import java.util.*;

/**
 * 最长公共子序列问题(Longest Common Subsequence), 打印所有的公共子序列.
 * Created by xifeng.yang on 2019/11/17.
 */
class PrintLCS {
    public static void main(String[] args) {
        String X = "ABCBDAB", Y = "BDCABA";

        PrintLCS printLCS = new PrintLCS();
        int[][] T = new int[X.length() + 1][Y.length() + 1];
        Set<String> lcs = printLCS.LCS(X, Y, T);
        System.out.print(lcs);
    }

    public Set<String> LCS(String X, String Y, int[][] T) {
        LCSLength(X, Y, T);

        List<String> lcs = LCS(X, Y, X.length(), Y.length(), T);
        return new HashSet<>(lcs);
    }

    private List<String> LCS(String X, String Y, int m, int n, int[][] T) {
        if (m == 0 || n == 0) {
            return new ArrayList<>(Collections.nCopies(1, ""));
        }

        if (X.charAt(m - 1) == Y.charAt(n - 1)) {
            List<String> lcs = LCS(X, Y, m - 1, n - 1, T);

            for (int i = 0; i < lcs.size(); i++) {
                lcs.set(i, lcs.get(i) + (X.charAt(m - 1)));
            }

            return lcs;
        }

        if (T[m - 1][n] > T[m][n - 1]) {
            return LCS(X, Y, m - 1, n, T);
        }

        if (T[m][n - 1] > T[m - 1][n]) {
            return LCS(X, Y, m, n - 1, T);
        }

        List<String> top = LCS(X, Y, m - 1, n, T);
        List<String> left = LCS(X, Y, m, n - 1, T);

        top.addAll(left);
        return top;
    }

    private void LCSLength(String X, String Y, int[][] T) {
        for (int i = 1; i <= X.length(); i++) {
            for (int j = 1; j <= Y.length(); j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    T[i][j] = T[i - 1][j - 1] + 1;
                } else {
                    T[i][j] = Integer.max(T[i - 1][j], T[i][j - 1]);
                }
            }
        }
    }
}