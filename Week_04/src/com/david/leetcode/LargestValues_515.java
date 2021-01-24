package com.david.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LargestValues_515 {
    public static void main(String[] args) {

    }

    /**
     * dfs
     */
    static class Solution1 {
        public List<Integer> largestValues(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) return res;
            dfs(root, 0, res);
            return res;
        }

        private void dfs(TreeNode root, int level, List<Integer> res) {
            if (res.size() == level) res.add(root.val);//需要新加一层
            else res.set(level, Math.max(res.get(level), root.val));
            if (root.left != null) dfs(root.left, level + 1, res);
            if (root.right != null) dfs(root.right, level + 1, res);
        }
    }

    /**
     * bfs
     */
    static class Solution {
        public List<Integer> largestValues(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) return res;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                Integer max = 0;
                int size = queue.size();//size一定到提前定义！！！ 放在循环里每次会变
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    max = Math.max(node.val, max);
                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                }
                res.add(max);
            }
            return res;
        }
    }
}
