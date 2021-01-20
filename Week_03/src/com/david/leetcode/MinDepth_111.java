package com.david.leetcode;

public class MinDepth_111 {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/solution/li-jie-zhe-dao-ti-de-jie-shu-tiao-jian-by-user7208/
     * 注意 这道题说的是到叶子节点的距离 1->2这样的树  1不是叶子节点 2是叶子节点最小高度为2
     */
    static class Solution {
        public int minDepth(TreeNode root) {
            if (root == null) return 0;//遍历到叶子节点了
            if (root.left == null) return minDepth(root.right) + 1;//左子树为空 要取右子树的最小高度
            if (root.right == null) return minDepth(root.left) + 1;//同上
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;//左右子树都存在 取小的加上当前层(+1)
        }
    }
}
