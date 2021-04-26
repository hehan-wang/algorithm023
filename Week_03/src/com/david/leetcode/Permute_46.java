package com.david.leetcode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO
 */
public class Permute_46 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> permute = new Solution1().permute(nums);
        System.out.println(permute);
    }

    /**
     * 回溯+深度优先(dfs)
     * time: O(n*n!)
     */
    static class Solution1 {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            if (nums == null) return res;//边界控制
            boolean[] used = new boolean[nums.length];//存使用过的数组
            ArrayDeque<Integer> stack = new ArrayDeque<>();//使用栈存临时变量为了添加删除O(1)
            dfs(nums, used, stack, res, 0);
            return res;
        }

        private void dfs(int[] nums, boolean[] used, ArrayDeque<Integer> curr, List<List<Integer>> res, int depth) {
            //terminator
            if (depth == nums.length) { //递归树层级打到depth了需要把临时结果复制存结果List
                res.add(new ArrayList<>(curr));
                return;
            }
            for (int i = 0; i < nums.length; i++) {//深度优先每次都取不重复第一条加到stack
                if (used[i]) continue;//当前节点用过了跳过
                //process 加到缓存中 加到临时结果中
                curr.addLast(nums[i]);
                used[i] = true;
                //drill down 下钻一层
                dfs(nums, used, curr, res, depth + 1);
                //reverse states 递归调用回来后 回溯状态
                curr.removeLast();
                used[i] = false;
            }
        }
    }

    static class Solution2 {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> output = Arrays.stream(nums).boxed().collect(Collectors.toList());
            backtrack(output, 0, nums.length, res);
            return res;
        }

        private void backtrack(List<Integer> output, int first, int n, List<List<Integer>> res) {
            if (first == n) res.add(new ArrayList<>(output));
            for (int i = first; i < n; i++) {
                Collections.swap(output, first, i);
                backtrack(output, first + 1, n, res);
                Collections.swap(output, first, i);
            }
        }
    }

    static class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();

            List<Integer> output = new ArrayList<Integer>();
            for (int num : nums) {
                output.add(num);
            }

            int n = nums.length;
            backtrack(n, output, res, 0);
            return res;
        }

        public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
            // 所有数都填完了
            if (first == n) {
                res.add(new ArrayList<Integer>(output));
            }
            for (int i = first; i < n; i++) {
                // 动态维护数组
                Collections.swap(output, first, i);
                // 继续递归填下一个数
                backtrack(n, output, res, first + 1);
                // 撤销操作
                Collections.swap(output, first, i);
            }
        }
    }
}

