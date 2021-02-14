package com.david.leetcode;

//同121
public class MaxProfit_188 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit(2, new int[]{2, 4, 1}));
        System.out.println(new Solution().maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}));
        System.out.println(new Solution1().maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}));
    }

    /**
     * 由于i只跟i-1相关 dp方程退化成O(k)
     */
    static class Solution1 {
        public int maxProfit(int k, int[] prices) {
            if (prices == null || prices.length == 0) return 0;
            int n = prices.length;
            if (k >= n / 2) return maxProfit122(prices);
            int[][] dp = new int[k + 1][2];
            for (int k1 = 1; k1 < k; k1++) {
                dp[k][0] = 0;
                dp[k][1] = -prices[0];
            }
            for (int i = 1; i < n; i++) {
                for (int k1 = k; k1 > 0; k1--) {
                    dp[k1][0] = Math.max(dp[k1][0], dp[k1][1] + prices[i]);
                    dp[k1][1] = Math.max(dp[k1][1], dp[k1 - 1][0] - prices[i]);
                }
            }
            return dp[k][0];
        }

        private int maxProfit122(int[] prices) {
            int p0 = 0, p1 = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                p0 = Math.max(p0, p1 + prices[i]);
                p1 = Math.max(p1, p0 - prices[i]);
            }
            return p0;
        }

    }

    /**
     * 因为两天才能产生收益 k>2/prices.length 相当于k正无穷转化为122题
     * 否则就是通用dp
     * time:O(kn)
     * space:O(2kn)
     */
    static class Solution {
        public int maxProfit(int k, int[] prices) {
            if (prices == null || prices.length == 0) return 0;
            int n = prices.length;
            if (k >= n / 2) return maxProfit122(prices);//优化dp方程为122题
            int[][][] dp = new int[n][k + 1][2]; //k+1是为了dp方程可以直接使用k 而不是k-1
            for (int k1 = 1; k1 <= k; k1++) {//把每次k都初始化 下标为0没有使用到
                dp[0][k1][0] = 0;
                dp[0][k1][1] = -prices[0];
            }
            for (int i = 1; i < n; i++) {
                for (int k1 = k; k1 > 0; k1--) {
                    dp[i][k1][0] = Math.max(dp[i - 1][k1][0], dp[i - 1][k1][1] + prices[i]);
                    dp[i][k1][1] = Math.max(dp[i - 1][k1][1], dp[i - 1][k1 - 1][0] - prices[i]);
                }
            }
            return dp[n - 1][k][0];
        }

        private int maxProfit122(int[] prices) {
            int p0 = 0, p1 = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                p0 = Math.max(p0, p1 + prices[i]);
                p1 = Math.max(p1, p0 - prices[i]);
            }
            return p0;
        }
    }
}
