package com.david.leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Codec_297 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3, new TreeNode(4), new TreeNode(5)));
        Codec ser = new Codec();
        Codec deser = new Codec();
        System.out.println(ser.serialize(root));
        TreeNode ans = deser.deserialize(ser.serialize(root));
        preorder(ans);
    }

    private static void preorder(TreeNode node) {
        if (node == null) return;
        System.out.println(node.val);
        preorder(node.left);
        preorder(node.right);
    }

    /**
     * 使用前序遍历
     * 序列化（根,左,右,x,)
     * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/solution/shou-hui-tu-jie-gei-chu-dfshe-bfsliang-chong-jie-f/
     */
    static public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return "x"; //x表示null
            String left = serialize(root.left);
            String right = serialize(root.right);
            return root.val + "," + left + "," + right;//每个子树左右子树构成
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            Deque<String> deque = new LinkedList<>(Arrays.asList(data.split(",")));
            return buildTree(deque);
        }

        private TreeNode buildTree(Deque<String> deque) {
            String rootStr = deque.pop();
            if ("x".endsWith(rootStr)) return null;//x为空节点
            TreeNode root = new TreeNode(Integer.parseInt(rootStr));
            root.left = buildTree(deque);
            root.right = buildTree(deque);
            return root;
        }

    }
}
