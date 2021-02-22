package com.david.leetcode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * TODO 超高频提 头条遇到过
 * https://leetcode-cn.com/problems/3sum/solution/3sumpai-xu-shuang-zhi-zhen-yi-dong-by-jyd/
 * 三数之和
 * 1.三次循环暴力法
 * 2.两次循环+Hash
 * 3.sort+夹逼
 */
public class ThreeSum_15 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
//        System.out.println(new Solution().threeSum(nums));
//        System.out.println(new Solution1().threeSum(nums));
        System.out.println(new Solution2().threeSum(nums));
    }

    /**
     * TODO 重要！！！ 双指针法
     * 犹豫不决先排序，步步逼近双指针
     * 1.先排序 保证有序便于拍重
     * 2.先把第一个元素nums[i]当成target
     * 3.双指针分别指向j=i+1,k=nums.length
     * 4.因为有序
     * 当nums[i]+nums[j]+nums[k]>0 证明大了 k--
     * 当nums[i]+nums[j]+nums[k]<0 证明小了 j++
     * 当nums[i]+nums[j]+nums[k]=0 放入结果数组
     * 技巧:使用while把重复数据过掉
     *
     * time:O(n2)
     *
     */
    static class Solution2 {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i < nums.length - 2; i++) {//留出双指针的位置
                if (nums[i] > 0) break;//如果第一个数大于0后面也都大于0 不会出现相加等于0的情况了
                if (i > 0 && nums[i] == nums[i - 1]) continue;//重复的target 只算一次
                int j = i + 1, k = nums.length - 1;//定义双指针 指向target+1和最后元素
                while (j < k) {//双指针相遇结束循环
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum > 0) {
                        while (j < k && nums[k] == nums[--k]) ;//sum>0 后指针前移到上一个不重复元素
                    } else if (sum < 0) {
                        while (j < k && nums[j] == nums[++j]) ;//sum<0 前指针后移到下一个不重复元素
                    } else {//相等的时候插入结果队列并前后指针向中间夹逼
                        res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        while (j < k && nums[k] == nums[--k]) ;
                        while (j < k && nums[j] == nums[++j]) ;
                    }
                }
            }
            return res;
        }
    }

    /**
     * a+b=-c
     * 先把-c存入map 转化成TwoSum+循环target
     * O(n2)
     */
    static class Solution1 {
        public List<List<Integer>> threeSum(int[] nums) {
            if (nums == null || nums.length < 2) return Collections.emptyList();
            Set<List<Integer>> set = new HashSet<>();
            for (int i = 0; i < nums.length - 2; i++) {
                int target = -nums[i];//取出第一个元素取反
                Map<Integer, Integer> cache = new HashMap<>();//缓存每次target变化重置
                for (int j = i + 1; j < nums.length; j++) {
                    int a = nums[j];
                    Integer b = cache.get(target - a);
                    if (b != null) {
                        List<Integer> l = Arrays.asList(a, b, -target);
                        l.sort(Comparator.naturalOrder());
                        set.add(l);
                    } else {
                        cache.put(a, a);
                    }
                }
            }
            return new ArrayList<>(set);
        }
    }

    /**
     * 暴力法
     * O(n^3)
     */
    static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            if (nums == null || nums.length < 2) return null;//数量小于2 不可能三数之和
            Set<List<Integer>> set = new HashSet<>();//使用set拍重
            Arrays.sort(nums);//先排序 确保内层list有序便于拍重
            for (int i = 0; i < nums.length - 2; i++) {// 0~length-2
                for (int j = i + 1; j < nums.length - 1; j++) { //i+1~length-1 确保用过的元素不会重复使用
                    for (int k = j + 1; k < nums.length; k++) {
                        if (nums[i] + nums[j] + nums[k] == 0) set.add(List.of(nums[i], nums[j], nums[k]));
                    }
                }
            }
            return new ArrayList<>(set);
        }
    }
}
