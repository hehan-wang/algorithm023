package com.david.leetcode;

import java.util.Arrays;

public class LengthOfLIS_300 {
    /**
     * dp方程存右边界为i的最大值 每次遍历(0,i] 如果i比j大的话 相当于可以把i接在子序列后面 所以+1
     * nums[i]>nums[j]  dp[i]=max(dp[i],dp[j]+1)
     */
    class Solution {
        public int lengthOfLIS(int[] nums) {
            int n = nums.length, max = 1;
            int[] dp = new int[n];
            Arrays.fill(dp, 1);//最小子序列也是自己 所以长度为1
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                max = Math.max(max, dp[i]);
            }
            return max;
        }
    }
}
