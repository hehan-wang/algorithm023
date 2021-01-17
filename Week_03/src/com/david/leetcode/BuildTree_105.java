package com.david.leetcode;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BuildTree_105 {
    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        TreeNode treeNode = new Solution().buildTree(preorder, inorder);
        inorder(treeNode);

    }

    public static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.println(root.val);
        inorder(root.right);
    }

    /**
     * 递归实现
     * time：O(n) 因为使用Map缓存
     * space：O(n) 存储结果数和map缓存需要额外空间
     * 思路：
     * 对于任意一颗树而言，前序遍历的形式总是
     * [ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]
     * <p>
     * 即根节点总是前序遍历中的第一个节点。而中序遍历的形式总是
     * [ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]
     * <p>
     * 1.从前序遍历根节点切分中序遍历节点
     * 2.然后构建root节点
     * 3.root.left=递归左子树 root.right=递归右子树
     */
    static class Solution {
        Map<Integer, Integer> inorderIndexMap;//缓存map

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            int right = inorder.length - 1;
            inorderIndexMap = IntStream.rangeClosed(0, right).boxed().collect(Collectors.toMap(i -> inorder[i], Function.identity()));//构建中序遍历缓存下标
            return buildTree(preorder, inorder, 0, right, 0, right);
        }

        public TreeNode buildTree(int[] preorder, int[] inorder, int preorderLeft, int preorderRight, int inorderLeft, int inorderRight) {
            if (preorderLeft > preorderRight) return null;//终止条件两边界相遇
            TreeNode root = new TreeNode(preorder[preorderLeft]);//构建当前节点
            Integer inorderRootIndex = inorderIndexMap.get(preorder[preorderLeft]);//从缓存中获取中序遍历root下标 前序遍历一个元素即为root元素
            int leftTreeSize = inorderRootIndex - inorderLeft;//获取左子树长度
            //分别取中序数组和前序数组 左子树的数组迭代 连接到当前节点左边
            root.left = buildTree(preorder, inorder, preorderLeft + 1, preorderLeft + leftTreeSize, inorderLeft, inorderRootIndex - 1);
            //分别取中序数组和前序数组 右子树的数组迭代 连接到当前节点左边
            root.right = buildTree(preorder, inorder, preorderLeft + leftTreeSize + 1, preorderRight, inorderRootIndex + 1, inorderRight);
            return root;
        }
    }
}
