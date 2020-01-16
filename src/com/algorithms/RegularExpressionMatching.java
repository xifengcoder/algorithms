package com.algorithms;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 * <p>
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 * <p>
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 * <p>
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RegularExpressionMatching {

    public static void main(String[] args) {
        String s = "aa";
        String p = "a*";
        RegularExpressionMatching instance = new RegularExpressionMatching();
        System.out.println(instance.isMatch(s, p));
    }


    /**
     * 方法一: 递归法
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        return isMatch(s, 0, p, 0);
    }

    private boolean isMatch(String s, int sStart, String p, int pStart) {
        //若p为空, 当且仅当s为空时匹配成功.
        if (pStart >= p.length()) {
            return sStart >= s.length();
        }

        //若s为空, 当且仅当s为"x*x*x*"这种形式时匹配成功.
        if (sStart >= s.length()) {
            return (p.length() - pStart >= 2 && p.charAt(pStart + 1) == '*') &&
                    isMatch(s, sStart, p, pStart - 2);
        }

        //若p的长度>=2,且第2个字符为'*', 则分两种情况:
        //1. p的前两个字符'x*'取0个;
        //2. 拿p的首字符'x'与s的首字符比较.
        if (p.length() - pStart >= 2 && p.charAt(pStart + 1) == '*') {
            return isMatch(s, sStart, p, pStart + 2) ||
                    ((p.charAt(pStart) == s.charAt(sStart) ||
                            p.charAt(pStart) == '.') &&
                            isMatch(s, sStart + 1, p, pStart));
        } else if (p.charAt(pStart) == '.') {
            //若p的首字符为'.',则可匹配s中的任意字符, 此时p和s都向后移一位匹配后面的字符串.
            return isMatch(s, sStart + 1, p, pStart + 1);
        } else {
            //若p的首字符和s的首字符相等, 则p和s都后移一位匹配后面的字符串;
            //否则返回false.
            return (p.charAt(pStart) == s.charAt(sStart)
                    && isMatch(s, sStart + 1, p, pStart + 1));
        }
    }

}