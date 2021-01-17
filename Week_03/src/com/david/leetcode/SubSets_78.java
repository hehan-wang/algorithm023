package com.david.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SubSets_78 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
//        List<List<Integer>> res = new Solution().subsets(nums);
//        List<List<Integer>> res = new Solution1().subsets(nums);
        List<List<Integer>> res = new Solution2().subsets(nums);
        System.out.println(res);
    }

    /**
     * 回溯
     */
    static class Solution2 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            backtrack(0, nums, res, new ArrayList<>());
            return res;
        }

        private void backtrack(int i, int[] nums, List<List<Integer>> res, List<Integer> tmp) {
            res.add(new ArrayList<>(tmp));//复制一份避免同一个引用
            for (int j = i; j < nums.length; j++) {//j从i开始避免重复
                tmp.add(nums[j]);
                backtrack(j + 1, nums, res, tmp);//drill down
                tmp.remove(tmp.size() - 1);//reverse states
            }
        }
    }

    /**
     * 分治
     * 分解成拿当前元素和不拿当前元素
     */
    static class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            if (nums == null) return res;
            sub(nums, 0, res, new ArrayList<>());
            return res;
        }

        private void sub(int[] nums, int index, List<List<Integer>> res, List<Integer> item) {
            //terminator
            if (index == nums.length) {
                res.add(new ArrayList<>(item));
                return;
            }
            //split
            //drill down
            sub(nums, index + 1, res, item); //不拿当前元素
            item.add(nums[index]);
            sub(nums, index + 1, res, item);//拿当前元素
            //revert states 删除最后一个
            item.remove(item.size() - 1);
        }
    }

    /**
     * 循环
     * 外层循环nums
     * 内层循环res 每次把前一步的结果加上nums[i] 在放入res集合
     * 注意：需要复制集合
     */
    static class Solution1 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(new ArrayList<>());//因为一定有空数组子集 直接加一个空数组便于循环
            Arrays.stream(nums).boxed()
                    .map(num -> res.stream()
                            .map(ArrayList::new)//new一个list 否则会使数组中都是同一个list对象
                            .peek(r -> r.add(num))//list里增加num元素
                            .collect(Collectors.toList()))
                    .forEach(res::addAll);
            return res;
        }
    }
}
