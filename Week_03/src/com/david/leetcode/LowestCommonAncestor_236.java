package com.david.leetcode;


public class LowestCommonAncestor_236 {
    public static void main(String[] args) {

    }

    /**
     * 类似后序遍历的方法
     * <p>
     * terminator：
     * 1.p为root的时候 p为q最近祖先。
     * 2.q为root同理
     * 3.root为null 证明叶子节点没找到p或q
     * <p>
     * process：
     * 1.left为null 证明在右边 返回right
     * 2.right为null 证明在左边 返回left
     * 3.否则在root两边返回root
     * <p>
     * drill down
     * root.left,
     * root.right
     * <p>
     * reverse states
     */
    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) return root;
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left == null) return right;
            if (right == null) return left;
            return root;
        }
    }

}
