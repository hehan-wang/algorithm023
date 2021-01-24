package com.david.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 使用bfs dfs解法
 */
public class GenerateParenthesis_22 {
    public static void main(String[] args) {
        List<String> res = new Solution().generateParenthesis(3);
        List<String> res1 = new Solution1().generateParenthesis(3);
        System.out.println(res);
        System.out.println(res1);
    }

    /**
     * bfs
     */
    static class Solution1 {
        static class Node {//模拟了调用栈的参数
            private String res;
            private int left;
            private int right;

            public Node(String res, int left, int right) {
                this.res = res;
                this.left = left;
                this.right = right;
            }
        }

        public List<String> generateParenthesis(int n) {
            List<String> l = new ArrayList<>();
            if (n == 0) return l;
            Queue<Node> queue = new LinkedList<>();
            queue.offer(new Node("", 0, 0));
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                if (node.left == n && node.right == n) {
                    l.add(node.res);
                }
                if (node.left < n) queue.offer(new Node(node.res + "(", node.left + 1, node.right));
                if (node.right < node.left) queue.offer(new Node(node.res + ")", node.left, node.right + 1));
            }
            return l;
        }
    }

    /**
     * dfs
     */
    static class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            dfs(0, 0, n, "", res);
            return res;
        }

        private void dfs(int left, int right, int n, String s, List<String> res) {
            if (left == n && right == n) {
                res.add(s);
                return;
            }
            if (left < n) dfs(left + 1, right, n, s + "(", res);
            if (right < left) dfs(left, right + 1, n, s + ")", res);
        }

    }
}
