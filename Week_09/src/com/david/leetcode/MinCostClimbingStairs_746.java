package com.david.leetcode;

public class MinCostClimbingStairs_746 {
    public static void main(String[] args) {
        System.out.println(new Solution1().minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
        System.out.println(new Solution().minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }

    static class Solution2 {
        public int minCostClimbingStairs(int[] cost) {
            int n = cost.length;
            for (int i = 2; i < n; i++) {
                cost[i] += Math.min(cost[i - 1], cost[i - 2]);
            }
            return Math.min(cost[n - 1], cost[n - 2]);
        }
    }

    /**
     * dp存站上台阶i 所需要花费、最后取(n-1 n-2)跨到天台的最小值+0(cost[+MAX])
     * dp[i]=min(dp[i-1],dp[i-2])+cost[i]
     * 最小值总总花费=min(走一步上来，走两步上来)+当前值
     */
    static class Solution1 {
        public int minCostClimbingStairs(int[] cost) {
            int n = cost.length;
            int[] dp = new int[n];
            dp[0] = cost[0];
            dp[1] = cost[1];
            for (int i = 2; i < n; i++) {
                dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
            }
            return Math.min(dp[n - 2], dp[n - 1]);
        }
    }


    /**
     * dp存到达i需要花费
     * dp[i]=min(dp[i-1]+cost[i-1],dp[i-2]+cost[i-2])
     * 初始条件： dp[0]=dp[1]=0 、因为从 0 1出发、 所以到达为0、数组初始化为0可以省略
     * dp[n] 为到达天台
     */
    static class Solution {
        public int minCostClimbingStairs(int[] cost) {
            int n = cost.length;
            int[] dp = new int[n + 1];
            for (int i = 2; i <= n; i++) {
                dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
            }
            return dp[n];
        }
    }
}
