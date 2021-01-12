package com.david.leetcode;

/**
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxArea_11 {
    public static void main(String[] args) {
        int[] nums = {1, 8, 6, 2, 5, 4, 8, 3, 7};
//        int maxArea = new Solution().maxArea(nums);
        int maxArea = new Solution1().maxArea(nums);
        System.out.println(maxArea);
    }

    /**
     * 左右夹逼的办法
     * i j 两个边界下标 高度小的边界向内收敛 才有可能取得更大的面积
     * 当i j相遇的时候结束
     * time:O(n)
     */
    static class Solution1 {
        public int maxArea(int[] height) {
            int maxArea = 0;
            for (int i = 0, j = height.length - 1; i < j; ) {
                int minHeight = height[i] < height[j] ? height[i++] : height[j--];//既取出最小值 又迭代
                int area = (j - i + 1) * minHeight;
                maxArea = Math.max(area, maxArea);
            }
            return maxArea;
        }
    }

    /**
     * 两层循环枚举所有面积取最大值 暴力法
     * time:O(n2)
     * space:O(1)
     */
    static class Solution {
        public int maxArea(int[] height) {
            int maxArea = 0;
            for (int i = 0; i < height.length; i++) {
                for (int j = i + 1; j < height.length; j++) {
                    int area = (j - i) * (Math.min(height[i], height[j]));
                    maxArea = Math.max(area, maxArea);
                }
            }
            return maxArea;
        }
    }
}
