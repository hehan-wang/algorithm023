package com.david.leetcode;

/**
 * https://leetcode-cn.com/circle/article/qiAgHn/
 * 股票通用dp
 * i:时间
 * k:交易次数
 * 0/1: 持有 非持有股票
 * 边界状态：
 * T[-1][k][0] = 0, T[-1][k][1] = -Infinity
 * T[i][0][0] = 0, T[i][0][1] = -Infinity
 * 没有股票没有交易时候为0
 * 有股票没有交易时候为负无穷
 * dp方程： 必须先买在卖
 * T[i][k][0]=max(T[i-1][k][0],T[i-1][k][1]+price[i]) 不持有股票天数的最大值=max(前一天不持有股票的最大值,前一天持有股票的最大值 卖出当天股票)
 * T[i][k][1]=max(T[i-1][k][1],T[i-1][k-1][0]-price[i]) 持有股票天数的最大值=max(前一天持有股票的最大值,前一天不持有股票的最大值 买入当天的股票) 买入才会使k-1
 */
public class MaxProfit_121 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(new Solution1().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(new Solution2().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    /**
     * 可以退化为两个变量可以完成
     * time:O(1)
     */
    static class Solution2 {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) return 0;
            int profit0 = 0, profit1 = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                profit0 = Math.max(profit0, profit1 + prices[i]);
                profit1 = Math.max(profit1, -prices[i]);
            }
            return profit0;
        }
    }

    /**
     * 使用dp
     * k=1
     * T[i][1][0] = max(T[i - 1][1][0], T[i - 1][1][1] + prices[i])
     * T[i][1][1] = max(T[i - 1][1][1], T[i - 1][0][0] - prices[i]) = max(T[i - 1][1][1], -prices[i])
     * k降维掉了
     * time:O(n)
     * space:O(2n)
     */
    static class Solution1 {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) return 0;
            int n = prices.length;
            int[][] dp = new int[n][2];
            dp[0][0] = 0;
            dp[0][1] = -prices[0];
            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            }
            return dp[n - 1][0];//最后一定是卖出股票比不卖出大
        }
    }

    /**
     * 只允许买一次 最高点卖最低点买
     */
    static class Solution {
        public int maxProfit(int[] prices) {
            int res = 0, min = Integer.MAX_VALUE;
            for (int price : prices) {
                min = Math.min(min, price);//取出最小值
                int profit = price - min;
                if (profit > 0) res = Math.max(res, profit);
            }
            return res;
        }
    }
}
