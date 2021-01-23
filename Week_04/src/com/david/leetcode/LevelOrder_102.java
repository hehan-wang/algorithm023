package com.david.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LevelOrder_102 {
    public static void main(String[] args) {

    }

    //bfs
    static class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) return res;
            Deque<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int currLevelSize = queue.size();
                List<Integer> currLevel = new ArrayList<>(currLevelSize);
                for (int i = 0; i < currLevelSize; i++) {
                    TreeNode node = queue.pop();
                    currLevel.add(node.val);
                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                }
                res.add(currLevel);
            }
            return res;
        }
    }

    //dfs
    static class Solution1 {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) return res;
            dfs(root, 0, res);
            return res;
        }

        public void dfs(TreeNode root, int level, List<List<Integer>> res) {
            if (root == null) return;
            if (level == res.size()) res.add(new ArrayList<>());
            res.get(level).add(root.val);
            dfs(root.left, level + 1, res);
            dfs(root.right, level + 1, res);
        }
    }
}
