package com.david.leetcode;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis_22 {
    public static void main(String[] args) {
        System.out.println(new Solution().generateParenthesis(3));
    }

    /**
     * 使用递归
     * 思路：
     * 1.生成n个) n个(的排列组合
     * 2.去掉不合法情况 左右括号个数都得小于n 而且右括号个数小于左括号
     */
    static class Solution {
        public List<String> generateParenthesis(int n) {
            ArrayList<String> res = new ArrayList<>();
            gen(0, 0, "", n, res);
            return res;
        }

        private void gen(int left, int right, String s, int n, ArrayList<String> res) {
            if (left == n && right == n) {
                res.add(s);
                return;
            }
            if (left < n) gen(left + 1, right, s + "(", n, res);
            if (right < left) gen(left, right + 1, s + ")", n, res);
        }
    }
}
