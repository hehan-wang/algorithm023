package com.david.leetcode.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 */
public class PreOrderTranserval_144 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        List<Integer> list = new Solution1().preorderTraversal(root);
//        List<Integer> list = new Solution1().preorderTraversal(root);
        System.out.println(list);
    }

    /**
     * 使用栈 跟递归思想一致
     * 根->左子树->右子树
     */
    static class Solution1 {
        public List<Integer> preorderTraversal(TreeNode root) {
            ArrayList<Integer> res = new ArrayList<>();
            if (root == null) return res;
            TreeNode curr = root;
            LinkedList<TreeNode> stack = new LinkedList<>();
            while (!stack.isEmpty() || curr != null) {
                while (curr != null) {
                    res.add(curr.val);
                    stack.push(curr);
                    curr = curr.left;
                }
                curr = stack.pop();
                curr = curr.right;
            }
            return res;
        }
    }

    /**
     * 使用递归 递归本质上就是语言提供的栈
     * time: O(n) 简单来说每个节点有且仅访问一次
     * space: O(n)
     */
    static class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            preorder(res, root);
            return res;
        }

        public void preorder(List<Integer> res, TreeNode curr) {
            if (curr == null) return;
            res.add(curr.val);
            preorder(res, curr.left);
            preorder(res, curr.right);
        }
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
