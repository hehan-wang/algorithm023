package com.david.leetcode;

import java.util.ArrayList;
import java.util.List;

public class MinimumTotal_120 {
    public static void main(String[] args) {
        List<List<Integer>> l = List.of(
                List.of(2),
                List.of(3, 4),
                List.of(6, 5, 7),
                List.of(4, 1, 8, 3)
        );
        System.out.println(new Solution().minimumTotal(l));
        System.out.println(new Solution1().minimumTotal(l));
        System.out.println(new Solution2().minimumTotal(l));
        System.out.println(new Solution3().minimumTotal(l));
    }

    /**
     * 一维数组dp
     * time:O(n^2)
     * space:O(n)
     */
    static class Solution3 {
        public int minimumTotal(List<List<Integer>> triangle) {
            int n = triangle.size();
            int[] dp = new int[n + 1];//滚动每一行dp
            for (int i = n - 1; i >= 0; i--) {//三角形高
                for (int j = 0; j <= i; j++) {//三角形长
                    //dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j)
                    dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);//由于每行可以滚动更新丢弃上一行可以使用一维数组
                }
            }
            return dp[0];
        }
    }

    /**
     * 二维数组dp 自底向上 上一层等于下一层左右最小值+自己
     * dp方程：
     * dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j)
     * dp[2,0]=Math.min(dp[3,0],dp[3,1])+triangle[2,0]
     * time:O(n^2)
     * spaceO(n^2)
     */
    static class Solution2 {
        public int minimumTotal(List<List<Integer>> triangle) {
            int n = triangle.size();
            int[][] dp = new int[n + 1][n + 1];
            //复用trangle为dp数组
            for (int i = n - 1; i >= 0; i--) {//因为最后一行遍历dp等于自身值 所以从倒数第二行开始
                for (int j = 0; j <= i; j++) {//因为是三角形 j<=i
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
                }
            }
            return dp[0][0];
        }
    }

    /**
     * dfs+记忆化搜索
     * time:O(n^2)
     * space:O(1)
     */
    static class Solution1 {
        public int minimumTotal(List<List<Integer>> triangle) {
            int[][] memo = new int[triangle.size()][triangle.size()];//缓存数组
            return dfs(0, 0, memo, triangle);
        }

        private int dfs(int i, int j, int[][] memo, List<List<Integer>> triangle) {
            if (i == triangle.size()) return 0;
            if (memo[i][j] != 0) return memo[i][j];
            int left = dfs(i + 1, j, memo, triangle);
            int right = dfs(i + 1, j + 1, memo, triangle);
            return Math.min(left, right) + triangle.get(i).get(j);
        }
    }

    /**
     * dfs 自上而下
     * 最近子问题：取左边和右边最小值
     * time:O(2^n)
     * space:O(1)
     */
    static class Solution {
        public int minimumTotal(List<List<Integer>> triangle) {
            return dfs(0, 0, triangle);
        }

        private int dfs(int i, int j, List<List<Integer>> triangle) {
            if (i == triangle.size()) return 0;
            int left = dfs(i + 1, j, triangle);
            int right = dfs(i + 1, j + 1, triangle);
            return Math.min(left, right) + triangle.get(i).get(j);
        }
    }
}
