package com.david.leetcode;

import java.util.Arrays;

public class CoinChange_322 {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(new Solution().coinChange(coins, amount));
        System.out.println(new Solution().coinChange(new int[]{2}, 3));
    }

    /**
     * dp
     * 取当前硬币和不取当前硬币的最小值
     * dp[i]=Math.min(dp[i],dp[j-coin[i]+1);
     * time:O(n2)
     * space:O(n)
     */
    static public class Solution {
        public int coinChange(int[] coins, int amount) {
            int max = amount + 1;//最大值选择amount+1 不使用Integer.MAX_VALUE 因为 Integer.MAX_VALUE+1 变成负数了
            int[] dp = new int[amount + 1];//循环amount次+1个dp[0]初始化
            Arrays.fill(dp, max);
            dp[0] = 0;//起始值标识amount为0的时候不可拿硬币
            for (int i = 1; i <= amount; i++) {
                for (int coin : coins) {
                    if (i >= coin) {//金额必须大于硬币面值才能选择是否取
                        dp[i] = Math.min(dp[i], dp[i - coin] + 1);//dp方程
                    }
                }
            }
            return dp[amount] > amount ? -1 : dp[amount];//dp[amount] > amount 证明取到的是原始值->证明没有可兑换的
        }
    }
}
