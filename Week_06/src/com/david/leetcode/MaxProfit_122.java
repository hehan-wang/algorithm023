package com.david.leetcode;

//同121
public class MaxProfit_122 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    /**
     * k=正无穷  k=k-1  k的维度可以降维
     * T[i][k][0] = max(T[i - 1][k][0], T[i - 1][k][1] + prices[i])
     * T[i][k][1] = max(T[i - 1][k][1], T[i - 1][k - 1][0] - prices[i]) = max(T[i - 1][k][1], T[i - 1][k][0] - prices[i])
     */
    static class Solution {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) return 0;
            int profit0 = 0, profit1 = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                profit0 = Math.max(profit0, profit1 + prices[i]);
                profit1 = Math.max(profit1, profit0 - prices[i]);
            }
            return profit0;
        }
    }
}
