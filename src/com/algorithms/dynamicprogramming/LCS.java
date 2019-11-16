package com.algorithms.dynamicprogramming;

import java.util.HashMap;

/**
 * 求最长公共子序列
 */
public class LCS {
    public static void main(String[] args) {

        LCS lcs = new LCS();
        String X = "ABCBDAB", Y = "BDCABA";
        System.out.println(lcs.LCSLength3(X, Y));
    }

    /**
     * 递归的版本
     */
    public int LCSLength(String X, String Y, int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }

        if (X.charAt(m - 1) == Y.charAt(n - 1)) {
            return LCSLength(X, Y, m - 1, n - 1) + 1;
        }

        return Integer.max(LCSLength(X, Y, m, n - 1),
                LCSLength(X, Y, m - 1, n));
    }


    /**
     * 动态规划, 自顶向下的方法.
     * 查找字符串X[0..m-1]和Y[0..n-1]的最长公共子序列的长度.
     * 时间复杂度O(mn),辅助空间复杂度O(mn).
     */
    public int LCSLength2(String X, String Y, int m, int n, HashMap<String, Integer> lookup) {

        if (m == 0 || n == 0) {
            return 0;
        }

        String key = m + "|" + n;

        if (!lookup.containsKey(key)) {

            if (X.charAt(m - 1) == Y.charAt(n - 1)) {
                lookup.put(key, LCSLength2(X, Y, m - 1, n - 1, lookup) + 1);
            } else {
                lookup.put(key, Integer.max(LCSLength2(X, Y, m - 1, n, lookup),
                        LCSLength2(X, Y, m, n - 1, lookup)));
            }
        }

        return lookup.get(key);
    }

    /**
     * 动态规划, 自底向上的方法.
     * 时间复杂度O(mn), 辅助空间复杂度O(mn).
     */
    public int LCSLength2(String X, String Y) {
        int m = X.length();
        int n = Y.length();

        int[][] lookup = new int[m + 1][n + 1]; //局部变量, 数组默认初始化为0.
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    lookup[i][j] = lookup[i - 1][j - 1] + 1;
                } else {
                    lookup[i][j] = Integer.max(lookup[i][j - 1], lookup[i - 1][j]);
                }
            }
        }

        return lookup[m][n];
    }

    /**
     * 动态规划, 自底向上的方法.
     * 时间复杂度O(mn), 辅助空间复杂度O(2n).
     * 思路:
     * i固定, LCS(i,j)保存在current[n+1]中, 即LCS(i,0),LCS(i,1),...,LCS(i,n)分别保存到current[0],current[1],...,current[n]中.
     * prev[n+1]保存i-1时的结果, 即LCS(i-1,j).
     * 因为LCS(i,j)的结果只依赖于当前行和前一行的结果, 所以只要缓存上一行的结果即可.
     */
    public int LCSLength3(String X, String Y) {
        int m = X.length(), n = Y.length();

        int[] curr = new int[n + 1];
        int[] prev = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + 1;
                } else {
                    curr[j] = Integer.max(prev[j], curr[j - 1]);
                }
            }

            System.arraycopy(curr, 0, prev, 0, n + 1);
        }

        return curr[n];
    }

    /**
     * 动态规划, 自底向上的方法.
     * 时间复杂度O(mn), 辅助空间复杂度O(n).
     * 思路:
     * i固定, LCS(i,j)保存在current[n+1]中, 即LCS(i,0),LCS(i,1),...,LCS(i,n)分别保存到current[0],current[1],...,current[n]中.
     * prev[n+1]保存i-1时的结果, 即LCS(i-1,j).
     * 因为LCS(i,j)的结果只依赖于当前行和前一行的结果, 所以只要缓存上一行的结果即可.
     */
    public static int LCSLength4(String X, String Y) {
        int m = X.length(), n = Y.length();

        int[] curr = new int[n + 1];
        int prev;

        for (int i = 0; i <= m; i++) {
            prev = curr[0];
            for (int j = 0; j <= n; j++) {
                int backup = curr[j];
                if (i == 0 || j == 0) {
                    curr[j] = 0;
                } else {
                    if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                        curr[j] = prev + 1;
                    } else {
                        curr[j] = Integer.max(curr[j], curr[j - 1]);
                    }
                }
                prev = backup;
            }
        }

        return curr[n];
    }
}
