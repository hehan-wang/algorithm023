package com.david.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 深度优先遍历
 */
public class Dfs {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        root.left = n2;
        root.right = n3;
        n3.right = n4;
        List<List<Integer>> res = levelorder(root);
        System.out.println(res);
    }

    //深度优先层序遍历
    public static List<List<Integer>> levelorder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, 0, res);
        return res;
    }

    public static void dfs(TreeNode root, int level, List<List<Integer>> res) {
        if (res.size() == level) res.add(new ArrayList<>());//新增加一层的时候初始化list
        res.get(level).add(root.val);//找到当前层list 放入自己
        if (root.left != null) dfs(root.left, level + 1, res);//下钻左孩子
        if (root.right != null) dfs(root.right, level + 1, res);//下钻右孩子
    }
}
