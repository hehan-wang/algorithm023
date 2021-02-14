package com.david.leetcode;

public class MaxProduct_152 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxProduct(new int[]{2, 3, -2, 4}));
        System.out.println(new Solution1().maxProduct(new int[]{2, 3, -2, 4}));
    }

    /**
     * 暴力法
     * 穷举遍历子数组的左右边界取最大值
     */
    static class Solution1 {
        public int maxProduct(int[] nums) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                int product = 1;
                for (int j = i; j < nums.length; j++) {
                    product *= nums[j];
                    max = Math.max(max, product);
                }
            }
            return max;
        }
    }

    /**
     * 动态规划
     * 思路：
     * 当前存储最大值最小值 因为存在负数最小值可能会变为最大值
     * time:O(n)
     * space:O(1)
     */
    static class Solution {
        public int maxProduct(int[] nums) {
            int max = Integer.MIN_VALUE, currMax = 1, currMin = 1;
            for (int num : nums) {
                if (num < 0) {//当前值小于0 调换最大值和最小值
                    int tmp = currMax;
                    currMax = currMin;
                    currMin = tmp;
                }
                currMax = Math.max(currMax * num, num);//取当前值与 前值*当前值的 最大值
                currMin = Math.min(currMin * num, num);
                max = Math.max(max, currMax);
            }
            return max;
        }
    }
}
