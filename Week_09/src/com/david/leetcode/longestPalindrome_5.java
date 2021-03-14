package com.david.leetcode;

/**
 * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
 * 1.暴力 遍历子串 验证回文 O(n^3)
 * 2.中心扩散 最快
 * 3.dp
 */
public class longestPalindrome_5 {
    public static void main(String[] args) {
        System.out.println("==================Solution11==========================");
        System.out.println(new Solution0().longestPalindrome("bb"));
        System.out.println(new Solution0().longestPalindrome("babad"));
        System.out.println(new Solution0().longestPalindrome("cbbd"));
        System.out.println(new Solution0().longestPalindrome("a"));
        System.out.println(new Solution0().longestPalindrome("bb"));
        System.out.println("==================Solution0==========================");
        System.out.println(new Solution0().longestPalindrome("bb"));
        System.out.println(new Solution0().longestPalindrome("babad"));
        System.out.println(new Solution0().longestPalindrome("cbbd"));
        System.out.println(new Solution0().longestPalindrome("a"));
        System.out.println(new Solution0().longestPalindrome("bb"));
//        System.out.println("==================Solution1==========================");
//        System.out.println(new Solution1().longestPalindrome("bb"));
//        System.out.println(new Solution1().longestPalindrome("babad"));
//        System.out.println(new Solution1().longestPalindrome("cbbd"));
//        System.out.println(new Solution1().longestPalindrome("a"));
//        System.out.println(new Solution1().longestPalindrome("bb"));
//        System.out.println(new Solution1().longestPalindrome("ac"));
//        System.out.println("==================Solution2==========================");
//        System.out.println(new Solution2().longestPalindrome("bb"));
//        System.out.println(new Solution2().longestPalindrome("babad"));
//        System.out.println(new Solution2().longestPalindrome("cbbd"));
//        System.out.println(new Solution2().longestPalindrome("a"));
//        System.out.println(new Solution2().longestPalindrome("bb"));
//        System.out.println(new Solution2().longestPalindrome("ac"));
    }


    /**
     * 中心扩散法
     * 枚举中心位置 -->比枚举左右界 降低一个数量级
     */
    static class Solution2 {
        public String longestPalindrome(String s) {
            if (s.length() == 0 || s.length() < 2) return s;
            int begin = 0, end = 0;//存前后下标
            char[] chars = s.toCharArray();
            for (int i = 0; i < s.length(); i++) {//i为中心下标
                int lenOdd = expand(chars, i, i);
                int lenEven = expand(chars, i, i + 1);
                int len = Math.max(lenOdd, lenEven);
                if (len > (end - begin + 1)) {
                    begin = i - (len - 1) / 2;
                    end = i + len / 2;
                }
            }

            return s.substring(begin, end + 1);
        }

        private int expand(char[] chars, int i, int j) {
            while (i >= 0 && j < chars.length && chars[i] == chars[j]) {
                i--;
                j++;
            }
            return j - i - 1;
        }
    }

    /**
     * dp 超
     */
    static class Solution11 {
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 2) return s;
            int begin = 0, maxLength = 1, n = s.length();//begin存第一个下标 maxLength存回文长度
            boolean[][] dp = new boolean[n][n];
            char[] chars = s.toCharArray();
            for (int i = n - 1; i >= 0; i--) {//后后向前
                for (int j = i; j < n; j++) {//从前向后
                    dp[i][j] = chars[i] == chars[j] && (j - i < 2 || dp[i + 1][j - 1]);

                    if (dp[i][j] && (j - i + 1 > maxLength)) {
                        maxLength = j - i + 1;
                        begin = i;
                    }
                }
            }
            return s.substring(begin, begin + maxLength);
        }
    }

    /**
     * dp
     * 状态转移：两边相等 子串为回文 当前串也是回文
     * dp方程：dp[i][j]= s[i]==s[j]&&dp[i-1]*dp[j+1]
     * 边界条件：j-i<3 证明中间只有子串一个元素
     */
    static class Solution1 {
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 2) return s;
            int begin = 0, maxLen = 1, n = s.length();
            boolean[][] dp = new boolean[n][n];
            char[] chars = s.toCharArray();
            for (int i = 0; i < n; i++) {
                dp[i][i] = true;//自己一个字符必定为回文串
            }
            for (int j = 0; j < n; j++) {
                for (int i = 0; i < j; i++) {
                    if (j - i < 3) dp[i][j] = (chars[i] == chars[j]);//中间为1个字符 如果左右两个字符相同为回文
                    else dp[i][j] = (chars[i] == chars[j]) && dp[i + 1][j - 1];//否则取决于  当前字符&&子串

                    if (dp[i][j] && j - i + 1 > maxLen) {//取最长
                        maxLen = j - i + 1;
                        begin = i;
                    }
                }
            }

            return s.substring(begin, begin + maxLen);
        }
    }

    /**
     * 暴力法不会超时
     */
    static class Solution0 {
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 2) return s;
            int maxLen = 0, begin = 0, n = s.length();
            char[] chars = s.toCharArray();
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    if (j - i + 1 > maxLen && isValidPalindrome(chars, i, j)) {
                        maxLen = j - i + 1;
                        begin = i;
                    }
                }
            }
            return s.substring(begin, begin + maxLen);
        }

        private boolean isValidPalindrome(char[] chars, int i, int j) {
            while (i < j) {
                if (chars[i] != chars[j]) return false;
                i++;
                j--;
            }
            return true;
        }
    }

    /**
     * 暴力法
     * 超出时间限制
     */
    static class Solution {
        public String longestPalindrome(String s) {
            if (s == null || s.length() == 0) return "";
            String res = String.valueOf(s.charAt(0));
            int n = s.length();
            for (int l = 0; l < n; l++) {
                loop:
                for (int r = l; r < n; r++) {
                    String sub = s.substring(l, r + 1);
                    for (int i = 0, j = sub.length() - 1; i < j; i++, j--) {//不符合条件直接跳出上级循环
                        if (sub.charAt(i) != sub.charAt(j)) continue loop;
                    }
                    res = sub.length() > res.length() ? sub : res;//是回文串取最长
                }
            }
            return res;
        }
    }
}
