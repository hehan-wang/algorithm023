package com.david.leetcode;

import java.util.Arrays;

public class UniquePaths_62 {
    public static void main(String[] args) {
        System.out.println(new Solution().uniquePaths(3, 7));
        System.out.println(new Solution1().uniquePaths(3, 7));
    }

    /**
     * 使用一维数组存dp方程 只存第一行 滚动更新数组
     * time:O(m*n)
     * space:O(m)
     */
    static class Solution1 {
        public int uniquePaths(int m, int n) {
            int[] dp = new int[n];
            Arrays.fill(dp, 1);
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    dp[j] += dp[j - 1];
                }
            }
            return dp[n - 1];
        }
    }

    /**
     * dp[i][j]存储从[0,0]到dp[i,j]的步数
     * dp方程：由于机器人只能向下或者向右走，所以 dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
     * time:O(m*n)
     * space:O(m*n)
     */
    static class Solution {
        public int uniquePaths(int m, int n) {
            int[][] dp = new int[m][n];
            for (int i = 0; i < n; i++) dp[0][i] = 1;//第一行只能从左边走过来 返回1
            for (int j = 0; j < m; j++) dp[j][0] = 1;//第一列只能从上面走过来 返回1
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
            return dp[m - 1][n - 1];
        }
    }
}
