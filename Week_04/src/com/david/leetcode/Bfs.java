package com.david.leetcode;

import java.util.*;

/**
 * 广度优先遍历
 */
public class Bfs {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        root.left = n2;
        root.right = n3;
        n3.right = n4;
        List<List<Integer>> list = levelOrder(root);
        System.out.println(list);
    }

    //广度优先层序遍历
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();//利用队列FIFO的特点
        queue.add(root);//加入第一层的根节点
        while (!queue.isEmpty()) {//一层循环一次
            int size = queue.size();//取当前层的个数
            List<Integer> level = new ArrayList<>();//存当前层的节点
            for (int i = 0; i < size; i++) {
                TreeNode first = queue.poll();
                level.add(first.val);//取出当前层元素
                if (first.left != null) queue.add(first.left);//放入下一层元素
                if ((first.right != null)) queue.add(first.right);//放入下一层元素
            }
            res.add(level);
        }
        return res;
    }

}
