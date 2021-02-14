package com.david.leetcode;

//同121
public class MaxProfit_309 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit(new int[]{1, 2, 3, 0, 2}));
        System.out.println(new Solution1().maxProfit(new int[]{1, 2, 3, 0, 2}));
    }

    /**
     * 优化O(1)空间复杂度
     */
    static class Solution1 {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) return 0;
            //p0:dp[i-1][0] p1:dp[i-1][1] pp0:dp[i-2][0]
            int p0 = 0, p1 = -prices[0], pp0 = 0;
            for (int i = 1; i < prices.length; i++) {
                int nextP0 = Math.max(p0, p1 + prices[i]);
                int nextP1 = Math.max(p1, pp0 - prices[i]);
                pp0 = p0;
                p0 = nextP0;
                p1 = nextP1;
            }
            return p0;
        }
    }

    /**
     * k无限同122
     * dp[i][k][0]=max(dp[i-1][k][0],dp[i-1][k][1]+prices[i])
     * dp[i][k][1]=max(dp[i-1][k][1],dp[i-2][k-1][0]-prices[i]) 当天持有必须前一天不能持有所以i-2
     * time:O(n)
     * space:O(n^2)
     */
    static class Solution {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) return 0;
            int n = prices.length;
            int[][] dp = new int[n][2];
            dp[0][0] = 0;
            dp[0][1] = -prices[0];
            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], (i < 2 ? 0 : dp[i - 2][0]) - prices[i]); //dp[0][k][0] dp[-1][k][0] 为0
            }
            return dp[n - 1][0];
        }
    }
}
