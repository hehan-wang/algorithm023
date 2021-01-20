package com.david.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class IsValidBST_98 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        root.right = new TreeNode(7, new TreeNode(6), new TreeNode(9));

//        boolean validBST = new Solution1().isValidBST(root);
        boolean validBST = new Solution().isValidBST(root);
        System.out.println(validBST);
    }

    /**
     * 使用栈 中序遍历 加个判断
     */
    static class Solution {
        public boolean isValidBST(TreeNode root) {
            LinkedList<TreeNode> stack = new LinkedList<>();
            Integer pre = Integer.MIN_VALUE;
            while (!stack.isEmpty() || root != null) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;//先入栈左节点
                }
                root = stack.pop();
                System.out.println(root.val);
                if (root.val <= pre) {//当前节点比前一个节点小不是BST
                    return false;
                }
                pre = root.val;
                root = root.right;
            }
            return true;
        }
    }

    /**
     * 使用左右边界
     */
    static class Solution1 {
        public boolean isValidBST(TreeNode root) {
            return helper(root, null, null);
        }

        public boolean helper(TreeNode root, Integer low, Integer high) {
            if (root == null) return true;
            if (low != null && root.val <= low) return false; //判断当前节点不在边界中返回false
            if (high != null && root.val >= high) return false;
            //找右子树 左边界设成当前值 ,找左子树右边界设成当前值 两个条件必须都满足才返回true
            return helper(root.right, root.val, high) && helper(root.left, low, root.val);
        }
    }
}
