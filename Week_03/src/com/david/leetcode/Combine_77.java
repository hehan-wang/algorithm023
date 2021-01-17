package com.david.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 */
public class Combine_77 {
    public static void main(String[] args) {
        List<List<Integer>> res = new Solution().combine(4, 2);
        System.out.println(res);
    }

    static class Solution {
        private List<List<Integer>> ans = new ArrayList<>();

        public List<List<Integer>> combine(int n, int k) {
            getCombine(n, k, 1, new ArrayList<>());
            return ans;
        }

        public void getCombine(int n, int k, int start, List<Integer> list) {
            if (k == 0) {
                ans.add(new ArrayList<>(list));
                return;
            }
            for (int i = start; i <= n - k + 1; i++) {
                list.add(i);
                getCombine(n, k - 1, i + 1, list);
                list.remove(list.size() - 1);
            }
        }
    }
}
