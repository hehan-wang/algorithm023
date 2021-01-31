package com.david.leetcode;

public class UniquePathsWithObstacles_63 {
    public static void main(String[] args) {

    }

    /**
     * 有障碍返回0否则同62题
     */
    static class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            if (obstacleGrid == null || obstacleGrid.length == 0) return 0;
            //定义dp数组
            int m = obstacleGrid.length;
            int n = obstacleGrid[0].length;
            int[][] dp = new int[m][n];
            for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) dp[i][0] = 1;//第一列无障碍设置成1 有障碍默认值0
            for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) dp[0][j] = 1;//第一行无障碍设置成1 有障碍默认值0
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (obstacleGrid[i][j] == 0) {
                        dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                    }
                }
            }
            return dp[m - 1][n - 1];
        }
    }
}
