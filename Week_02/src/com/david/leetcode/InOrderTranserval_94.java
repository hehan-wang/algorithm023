package com.david.leetcode;

import javax.swing.tree.TreeNode;
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
public class InOrderTranserval_94 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
//        List<Integer> list = new Solution().inorderTraversal(root);
        List<Integer> list = new Solution1().inorderTraversal(root);
        System.out.println(list);
    }

    /**
     * 使用栈 跟递归思想一致
     */
    static class Solution1 {
        public List<Integer> inorderTraversal(TreeNode root) {
            TreeNode curr = root;
            List<Integer> res = new ArrayList<>();
            Deque<TreeNode> stack = new LinkedList<>();
            while (curr != null || !stack.isEmpty()) {//终止条件：所有元素出栈且元素都遍历过了
                while (curr != null) {//先入栈左子树
                    stack.push(curr);
                    curr = curr.left;
                }
                curr = stack.pop();//弹栈放入list
                res.add(curr.val);
                curr = curr.right;//迭代右子树
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
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            inorder(res, root);
            return res;
        }

        public void inorder(List<Integer> res, TreeNode root) {
            if (root == null) return;
            inorder(res, root.left);
            res.add(root.val);
            inorder(res, root.right);
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
