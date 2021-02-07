package com.david.leetcode;

public class Rob_198 {
    public static void main(String[] args) {
        System.out.println(new Solution().rob(new int[]{1, 2, 3, 1}));
        System.out.println(new Solution1().rob(new int[]{1, 2, 3, 1}));
        System.out.println(new Solution2().rob(new int[]{1, 2, 3, 1}));
    }

    /**
     * 高级写法 由于只存dp[i-1] dp[i-2]可是使用两个变量
     */
    static class Solution2 {
        public int rob(int[] nums) {
            int pre = 0;//前两个
            int now = 0;//前一个
            for (int n : nums) {
                int tmp = now;//临时变量
                now = Math.max(pre + n, now);//dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
                pre = tmp;//把now的值付给pre
            }
            return now;
        }
    }

    /**
     * 中级写法 一维dp数组
     * time:O(n)
     * space:O(2n)
     * dp方程：取打劫前一个和打劫当前的(打劫前两个+当前)最大值
     * dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
     */
    static class Solution1 {
        public int rob(int[] nums) {
            //边界条件
            int n = nums.length;
            if (n == 0) return 0;
            if (n == 1) return nums[0];
            int[] dp = new int[n];
            dp[0] = nums[0];//第一次只能抢第一个
            dp[1] = Math.max(dp[0], dp[1]);//第二次取最前两个的最大值
            for (int i = 2; i < n; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }
            return dp[n - 1];
        }
    }

    /**
     * 初级写法 二维dp数组
     * time:O(n)
     * space:O(2n)
     * dp方程：
     * dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);//当前不抢 取上次抢和不抢的最大值
     * dp[i][1] = dp[i - 1][0] + nums[i];//当前抢的话上次不能抢
     */
    static class Solution {
        public int rob(int[] nums) {
            int n = nums.length;
            int[][] dp = new int[n][2];//dp方程 1为抢 0为不抢
            dp[0][0] = 0;//不抢第一个
            dp[0][1] = nums[0];//抢第一个
            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);//当前不抢 取上次抢和不抢的最大值
                dp[i][1] = dp[i - 1][0] + nums[i];//当前抢的话上次不能抢
            }
            return Math.max(dp[n - 1][0], dp[n - 1][1]);
        }
    }
}
