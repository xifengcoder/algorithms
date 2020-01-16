package com.algorithms.dynamicprogramming;

/**
 * 给定一个字符串(s)和一个字符模式(p)，实现一个支持'?'和'*'的通配符匹配。
 * <p>
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符?和*。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/wildcard-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Created by xifeng.yang on 2020/1/15
 */
public class WildcardMatching {

    public static void main(String[] args) {
        WildcardMatching instance = new WildcardMatching();
        String s = "aa";
        String p = "*";

        Boolean[] b = new Boolean[5];
        System.out.println(b[0]);
        boolean result = instance.isMatch(s, p);
        System.out.println("result = " + result);
    }

    public boolean isMatch(String s, String p) {
        //这里使用Boolean来存储, 因为boolean默认是会初始化的.
        Boolean[][] lookup = new Boolean[s.length() + 1][p.length() + 1];
        return isMatching(s, s.length() - 1, p, p.length() - 1, lookup);
    }

    private boolean isMatching(String s, int n, String p, int m, Boolean[][] table) {
        if (n < 0 && m < 0) {
            return true;
        } else if (m < 0) {
            return false;
        } else if (n < 0) {
            for (int i = 0; i <= m; i++) {
                if (p.charAt(i) != '*') {
                    return false;
                }
            }

            return true;
        }


        if (table[n][m] == null) {
            if (p.charAt(m) == '*') {
                table[n][m] = isMatching(s, n - 1, p, m, table) ||
                        isMatching(s, n, p, m - 1, table);
            } else {
                if (p.charAt(m) != '?' && p.charAt(m) != s.charAt(n)) {
                    table[n][m] = false;
                } else {
                    table[n][m] = isMatching(s, n - 1, p, m - 1, table);
                }
            }
        }

        return table[n][m];
    }

    public boolean isMatch2(String str, String pattern) {
        // get length of String and wildcard pattern
        int n = str.length();
        int m = pattern.length();

        // create a DP lookup table
        boolean[][] T = new boolean[n + 1][m + 1];

        // if both pattern and String is empty : match
        T[0][0] = true;

        // handle empty String case (i == 0)
        for (int j = 1; j <= m; j++) {
            if (pattern.charAt(j - 1) == '*') {
                T[0][j] = T[0][j - 1];
            }
        }

        // build matrix in bottom-up manner
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (pattern.charAt(j - 1) == '*') {
                    T[i][j] = T[i - 1][j] || T[i][j - 1];
                } else if (pattern.charAt(j - 1) == '?' ||
                        str.charAt(i - 1) == pattern.charAt(j - 1)) {
                    T[i][j] = T[i - 1][j - 1];
                }
            }
        }

        // last cell stores the answer
        return T[n][m];
    }
}
