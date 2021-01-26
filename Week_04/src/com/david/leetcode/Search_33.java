package com.david.leetcode;

public class Search_33 {
    public static void main(String[] args) {
        int[] a = {1, 3, 6, 22, 45, 13234, 134241};
        System.out.println("index:" + new Solution1().search(a, 22));
    }

    /**
     * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/ji-jian-solution-by-lukelee/
     * 硬上二分搜索模板 简洁写法
     */
    static class Solution {
        public int search(int[] nums, int target) {
            //边界条件判断
            if (nums == null || nums.length == 0) return -1;
            //二分搜索
            int n = nums.length - 1;
            int lo = 0, hi = n;
            while (lo <= hi) {
                int mid = lo + (hi - lo) >> 1;
                if (nums[mid] == target) return mid;//找到退出
                if (nums[0] < nums[mid]) {//左边有序
                    if (target >= nums[0] && target < nums[mid]) {
                        hi = mid - 1;
                    } else {
                        lo = mid + 1;
                    }
                } else {//右边有序
                    if (target > nums[mid] && target <= nums[n]) {
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }
            }
            return -1;
        }
    }

    /**
     * nums[0]小于mid 证明左边递增 否则右边递增
     */
    static class Solution1 {
        public int search(int[] nums, int target) {
            //边界条件判断
            if (nums == null || nums.length == 0) return -1;
            //二分搜索
            int n = nums.length - 1;
            int lo = 0, hi = n;
            while (lo <= hi) {
                int mid = lo + (hi - lo) >> 1;
                if (nums[mid] == target) return mid;//找到退出
                if (nums[0] < nums[mid]) {//左边有序
                    if (target >= nums[0] && target < nums[mid]) {
                        hi = mid - 1;
                    } else {
                        lo = mid + 1;
                    }
                } else {//右边有序
                    if (target > nums[mid] && target <= nums[n]) {
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }
            }
            return -1;
        }
    }
}
