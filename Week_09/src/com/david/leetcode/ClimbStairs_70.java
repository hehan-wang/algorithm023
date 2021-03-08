package com.david.leetcode;

/**
 * TODO
 * 1.每次上1，2，3级
 * 2.每次爬楼梯 [1..n]
 * 3.
 */
public class ClimbStairs_70 {
    public static void main(String[] args) {
        System.out.println(new Solution1().climbStairs(2));
        System.out.println(new Solution1().climbStairs(3));
    }

    /**
     * dp[i]=dp[i-2]+dp[i-1]
     * 一维dp可以退化成两个变量 存 dp[i-1] dp[i-2]
     */
    static class Solution1 {
        public int climbStairs(int n) {
            if (n <= 2) return n;
            int a = 1, b = 2, c = 3;
            for (int i = 3; i < n; i++) {
                a = b;//迭代n-2
                b = c;//迭代n-1
                c = a + b;
            }
            return c;
        }
    }

    /**
     * 可以从前一级台阶上一步 或者前两级台阶上两步
     * dp[i]=dp[i-1]+dp[i-2]
     */
    static class Solution {
        public int climbStairs(int n) {
            int[] dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }
    }
}
