package com.david.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum_18 {
    public static void main(String[] args) {
        int[] nums = {-2, -1, -1, 1, 1, 2, 2};
        System.out.println(new Solution().fourSum(nums, 0));
        System.out.println(new Solution1().fourSum(nums, 0));
    }

    /**
     * 相当于ThreeSum 再套一层循环
     */
    static class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> res = new ArrayList<>();
            int n = nums.length;
            if (n < 4) return res;
            Arrays.sort(nums);
            for (int i = 0; i < n - 3; i++) {
                //判断非法条件
                if (i > 0 && nums[i] == nums[i - 1]) continue;//排除掉重复的数
                if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;//最小的和都超了 直接返回
                if (nums[i] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) continue;//证明当前数太小了 直接跳过
                for (int j = i + 1; j < n - 2; j++) {
                    if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                    if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                    if (nums[i] + nums[j] + nums[n - 2] + nums[n - 1] < target) continue;
                    int left = i + 1, right = n - 1;
                    while (left < right) {
                        int sum = nums[i] + nums[j] + nums[left] + nums[right];
                        if (sum == target) {
                            res.add(List.of(nums[i], nums[j], nums[left], nums[right]));
                            while (left < right && nums[left] == nums[++left]) ;
                            while (left < right && nums[right] == nums[--right]) ;
                        } else if (sum < target) {
                            while (left < right && nums[left] == nums[++left]) ;
                        } else {
                            while (left < right && nums[right] == nums[--right]) ;
                        }
                    }
                }
            }
            return res;
        }
    }


    //第二遍
    static class Solution1 {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> res = new ArrayList<>();
            int length = nums.length;
            if (length < 4) return res;
            Arrays.sort(nums);
            for (int i = 0; i < length - 3; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
                if (nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) continue;
                for (int j = i + 1; j < length - 2; j++) {
                    if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                    if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                    if (nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) continue;
                    int left = i + 1, right = length - 1;
                    while (left < right) {
                        int sum = nums[i] + nums[j] + nums[left] + nums[right];
                        if (sum == target) {
                            res.add(List.of(nums[i], nums[j], nums[left], nums[right]));
                            while (left < right && nums[right] == nums[--right]) ;
                            while (left < right && nums[left] == nums[++left]) ;
                        } else if (sum > target) {
                            while (left < right && nums[right] == nums[--right]) ;
                        } else {
                            while (left < right && nums[left] == nums[++left]) ;
                        }
                    }
                }
            }
            return res;
        }
    }
}
