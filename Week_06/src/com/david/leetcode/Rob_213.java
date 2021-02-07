package com.david.leetcode;

import java.util.Arrays;

public class Rob_213 {
    public static void main(String[] args) {
        System.out.println(new Solution().rob(new int[]{2, 3, 2}));
    }

    /**
     * 把环形问题打断成[1~n] [0~n-1] 同198取最大值
     */
    static class Solution {
        public int rob(int[] nums) {
            if (nums.length == 0) return 0;
            if (nums.length == 1) return nums[0];
            return Math.max(
                    rob1(Arrays.copyOfRange(nums, 1, nums.length)),
                    rob1(Arrays.copyOfRange(nums, 0, nums.length - 1)));
        }


        public int rob1(int[] nums) {
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
}
