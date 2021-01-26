package com.david.leetcode;

import java.util.Arrays;

public class MoveZeroes_283 {
    public static void main(String[] args) {
        int[] nums = {1, 0, 0, 3, 12};
//        int[] nums = {0, 1, 0, 3, 12};
        new Solution().moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    static class Solution {
        public void moveZeroes(int[] nums) {
            int j = 0;//存第一个0元素的下标
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {//不为0的话交换
                    int n = nums[i];
                    nums[i] = nums[j];
                    nums[j++] = n;//遇到不为0 j+1
                }
            }
        }
    }
}
