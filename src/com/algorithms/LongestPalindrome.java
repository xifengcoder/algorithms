package com.algorithms;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 经典问题: 最长回文子串问题。
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Created by xifeng.yang on 2019/12/31
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        String s = "babad";
        LongestPalindrome o = new LongestPalindrome();
        System.out.println(o.longestPalindrome2(s));
    }

    /**
     * 解法一: 暴力法，算法复杂度O(n^3)
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 0) {
            return s;
        }

        String longestPalindrome = s.substring(0, 1);

        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if ((j - i + 1 > longestPalindrome.length()) && isPalindrome(s, i, j)) {
                    longestPalindrome = s.substring(i, j + 1);
                }
            }
        }

        return longestPalindrome;
    }

    /**
     * 解法二: 中心扩散法, 算法复杂度O(n^2).
     *
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        String longestPalindrome = "";
        int low, high;
        for (int i = 0; i < s.length() - 1; i++) {
            //以i为中心, 向两边扩散.
            low = i;
            high = i;
            while (low >= 0 && high < s.length()) {
                if (s.charAt(low) == s.charAt(high)) {
                    if ((high - low + 1) > longestPalindrome.length()) {
                        longestPalindrome = s.substring(low, high + 1);
                    }

                    low--;
                    high++;
                } else {
                    break;
                }
            }

            //以[i,i+1]为中心, 向两边扩散.
            low = i;
            high = i + 1;
            while (low >= 0 && high < s.length()) {
                if (s.charAt(low) == s.charAt(high)) {
                    if ((high - low + 1) > longestPalindrome.length()) {
                        longestPalindrome = s.substring(low, high + 1);
                    }

                    low--;
                    high++;
                } else {
                    break;
                }
            }
        }

        return longestPalindrome;
    }

    /**
     * 解法三: 添加剪枝版本
     *
     * @param s
     * @return
     */
    public String longestPalindrome3(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        String longestPalindrome = "";
        int low, high;
        int upperLimit;
        for (int i = 0; i < s.length() - 1; i++) {

            //以i为中心, 向两边扩散.
            low = i;
            high = i;
            upperLimit = Integer.min(2 * i + 1, 2 * (s.length() - 1 - i) + 1);
            while (low >= 0 && high < s.length() && longestPalindrome.length() <= upperLimit) {
                if (s.charAt(low) == s.charAt(high)) {
                    if ((high - low + 1) > longestPalindrome.length()) {
                        longestPalindrome = s.substring(low, high + 1);
                    }

                    low--;
                    high++;
                } else {
                    break;
                }
            }

            //以[i, i+1]为中心向两边扩散.
            low = i;
            high = i + 1;
            upperLimit = Integer.min(2 * i + 2, 2 * (s.length() - i - 2) + 2);

            while (low >= 0 && high < s.length() && longestPalindrome.length() <= upperLimit) {
                if (s.charAt(low) == s.charAt(high)) {
                    if (high - low + 1 > longestPalindrome.length()) {
                        longestPalindrome = s.substring(low, high + 1);
                    }

                    low--;
                    high++;
                } else {
                    break;
                }
            }
        }

        return longestPalindrome;
    }

    /**
     * 解法四: 动态规划解法, 算法复杂度O(n^2).
     *
     * @param s
     * @return
     */
    public String longestPalindromeDP(String s) {

        if (s == null || s.length() <= 1) {
            return s;
        }

        String longestPalindrome = "";
        int max = 0;

        //dp[start][end]表示S[start..end]是否是回文串.
        boolean[][] dp = new boolean[s.length()][s.length()];

        //S[start][end]是回文串, 当且仅当以下条件之一满足时:
        //1. S子串只有1个元素、或S子串有2个元素且首尾两个相等、或S子串有3个元素且首尾两个相等;
        //2. S子串有超过3个元素、且首尾两个相等、且除去首尾两个元素后剩余的为回文串.
        for (int end = 0; end < s.length(); end++) {
            for (int start = 0; start <= end; start++) {
                dp[start][end] = s.charAt(start) == s.charAt(end) && (end - start <= 2 || dp[start + 1][end - 1]);
                if (dp[start][end]) {
                    if (end - start + 1 > max) {
                        max = end - start + 1;
                        longestPalindrome = s.substring(start, end + 1);
                    }
                }
            }
        }
        return longestPalindrome;
    }

    /**
     * 解法五: 中心扩散法: 算法效率最高.
     * <p>
     * 执行耗时2ms, 在所有 Java 提交中击败了99.97%的用户.
     * <p>
     * 遍历每一个索引, 以该索引为中心, 向两边扩散, 看最多能扩散多远. 注意一个细节:
     * "回文中心"并不局限于一个字符, 也可能是从该索引起、连续为同一字符的一片区域.
     *
     * @param s
     * @return
     */
    public String longestPalindrome5(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        //保存起始位置，测试了用数组似乎能比全局变量稍快一点
        int[] range = new int[2];
        char[] str = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            //把回文看成中间的部分全是同一字符，左右部分相对称. 找到下一个与当前字符不同的字符。
            i = findLongest(str, i, range);
        }
        return s.substring(range[0], range[1] + 1);
    }

    /**
     * 查找以position为中心的最长回文串.
     * 返回与str[position]相同的最右边元素的下标.
     *
     * @param str
     * @param position
     * @param range
     * @return
     */
    private int findLongest(char[] str, int position, int[] range) {
        //查找中间部分, str[low..high]为中间部分.
        int low = position;
        int high = position;
        while (high < str.length - 1 && str[high + 1] == str[low]) {
            high++;
        }

        //result为与position下标元素相同的最右边元素下标.
        int result = high;
        while (low > 0 && high < str.length - 1 && str[low - 1] == str[high + 1]) {
            low--;
            high++;
        }

        //此时[low..high]为回文串.
        if (high - low > range[1] - range[0]) {
            range[0] = low;
            range[1] = high;
        }

        return result;
    }

    /**
     * 解法六: 滑动窗口法, 算法复杂度O(n^3).
     *
     * @param s
     * @return
     */
    public String longestPalindrome6(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        int maxSize = 0;
        String longestPalindrome = "";

        for (int i = 0, j = i; i < s.length(); i++) {
            while (j < s.length()) {
                if (isPalindrome(s, i, j) && j - i + 1 > maxSize) {
                    maxSize = j - i + 1;
                    longestPalindrome = s.substring(i, j + 1);
                }

                j++;
            }

            if (i + maxSize - 1 >= s.length()) {
                return longestPalindrome;
            } else {
                j = i + maxSize + 1;
            }
        }

        return longestPalindrome;
    }

    /**
     * 解法七: Manacher算法, 时间复杂度O(n), 空间复杂度O(n).
     *
     * @param s
     * @return
     */
    public String longestPalindrome7(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        StringBuilder sb = new StringBuilder("$#");

        for (int i = 0; i < s.length(); i++) {
            sb.append(String.valueOf(s.charAt(i)));
            sb.append("#");
        }

        char[] str = sb.toString().toCharArray();
        int[] r = new int[str.length];
        int max = 0, id = 0, ansR = 0, ansCenter = 0;


        for (int i = 1; i < str.length; i++) {
            r[i] = max - i > r[i] ? Math.min(r[2 * id - i], max - i) : 1;
            while (i - r[i] >= 0 && i + r[i] < str.length
                    && str[i - r[i]] == str[i + r[i]]) {
                r[i]++;
            }

            if (i + r[i] > max) {
                max = i + r[i];
                id = i;
            }

            if (ansR < r[i]) {
                ansR = r[i];
                ansCenter = i;
            }
        }
        int maxStart = (ansCenter - ansR + 1) / 2;
        return s.substring(maxStart, maxStart + ansR - 1);
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }

        return true;
    }
}
