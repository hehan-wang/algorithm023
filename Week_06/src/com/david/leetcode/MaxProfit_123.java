package com.david.leetcode;

//同121
public class MaxProfit_123 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
    }

    /**
     * k=2 枚举所有取每一次钱的4中情况
     * T[i][2][0] = max(T[i - 1][2][0], T[i - 1][2][1] + prices[i])
     * T[i][2][1] = max(T[i - 1][2][1], T[i - 1][1][0] - prices[i])
     * T[i][1][0] = max(T[i - 1][1][0], T[i - 1][1][1] + prices[i])
     * T[i][1][1] = max(T[i - 1][1][1], T[i - 1][0][0] - prices[i]) = max(T[i - 1][1][1], -prices[i])
     */
    static class Solution {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) return 0;
            int profit20 = 0, profit21 = -prices[0], profit10 = 0, profit11 = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                profit20 = Math.max(profit20, profit21 + prices[i]);
                profit21 = Math.max(profit21, profit10 - prices[i]);
                profit10 = Math.max(profit10, profit11 + prices[i]);
                profit11 = Math.max(profit11, -prices[i]);
            }
            return profit20;
        }
    }
}
