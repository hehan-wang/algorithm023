package com.david.leetcode;

//同121
public class MaxProfit_714 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }

    /**
     * 把手续费加在买入或者卖出的时候
     * T[i][k][0] = max(T[i - 1][k][0], T[i - 1][k][1] + prices[i])
     * T[i][k][1] = max(T[i - 1][k][1], T[i - 1][k][0] - prices[i] - fee)
     */
    static class Solution {
        public int maxProfit(int[] prices, int fee) {
            if (prices == null || prices.length == 0) return 0;
            int p0 = 0, p1 = -prices[0] - fee;
            for (int i = 1; i < prices.length; i++) {
                p0 = Math.max(p0, p1 + prices[i]);
                p1 = Math.max(p1, p0 - prices[i] - fee);
            }
            return p0;
        }
    }
}
