package com.david.leetcode;

public class LongestCommonSubsequence_1143 {
    public static void main(String[] args) {
        System.out.println(new Solution().longestCommonSubsequence("abcde", "ace"));
    }

    /**
     * dp
     * 推导过程
     * 1.s1="" ,s2=任意字符串  返回0
     * 2.s1="A" ,s2=任意字符串 A在s2中 返回1
     * 3.s1="...A" ,s2="...A" 最后一个字母相等证明 等于前一个最长子序列+1  返回dp[i + 1][j + 1] = dp[i][j] + 1;
     */
    static class Solution {
        public int longestCommonSubsequence(String text1, String text2) {
            int m = text1.length(), n = text2.length();
            int[][] dp = new int[m + 1][n + 1];//text1 x轴 text2 y轴
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    char c1 = text1.charAt(i), c2 = text2.charAt(j);
                    if (c1 == c2) {//最后一个字符相等 等于前一个dp最优解+1
                        dp[i + 1][j + 1] = dp[i][j] + 1;
                    } else {//否则取各回退一个字符的最优解
                        dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                    }
                }
            }
            return dp[m][n];
        }
    }
}
