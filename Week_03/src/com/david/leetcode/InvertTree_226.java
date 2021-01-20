package com.david.leetcode;

public class InvertTree_226 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        root.right = new TreeNode(7, new TreeNode(6), new TreeNode(9));

//        TreeNode node = new Solution().invertTree(root);
        TreeNode node = new Solution1().invertTree(root);
        inorder(node);
    }

    private static void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.println(node.val);
        inorder(node.right);
    }

    /**
     * 自顶向下交换
     */
    static class Solution1 {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) return null;
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            invertTree(root.left);
            invertTree(root.right);
            return root;
        }
    }

    /**
     * 自底向上交换
     */
    static class Solution {
        public TreeNode invertTree(TreeNode root) {
            //terminator
            if (root == null) return null;
            //process
            //drill down
            //从叶子节点先交换可以避免使用临时变量
            TreeNode rightSub = invertTree(root.right);//存右子树
            TreeNode leftSub = invertTree(root.left);//存左子树
            root.left = rightSub;//当前左孩子设置成右子树
            root.right = leftSub;//当前右孩子设置成左子树
            return root;
            //revert states
        }
    }
}
