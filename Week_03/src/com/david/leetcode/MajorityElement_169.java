package com.david.leetcode;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MajorityElement_169 {
    public static void main(String[] args) {
        int[] nums = {3, 2, 3};
//        int i = new Solution().majorityElement(new int[]{3, 2, 3});
        int i = new Solution2().majorityElement(nums);
        System.out.println(i);
    }

    /**
     * 摩尔投票法
     * 由于存在一个数数量>n/2
     * 一对一抵消最终肯定为﹢
     */
    static class Solution2 {
        public int majorityElement(int[] nums) {
            int candidate = nums[0];
            int vote = 0;
            for (int num : nums) {
                if (vote == 0) {
                    candidate = num;
                }
                vote += candidate == num ? 1 : -1;
            }
            return candidate;
        }
    }

    /**
     * 计数法
     * 遍历一次存map中计数
     * 过滤map中大于value 大于长度一半的值
     */
    static class Solution1 {
        public int majorityElement(int[] nums) {
            return Arrays.stream(nums).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().filter(e -> e.getValue() > nums.length >> 1).map(Map.Entry::getKey).findFirst().orElse(-1);
        }
    }

    /**
     * 排序法 排序后中间的数一定是要找的数
     * time:O(nlogn) 排序复杂度
     */
    static class Solution {
        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length >> 1];
        }
    }
}
