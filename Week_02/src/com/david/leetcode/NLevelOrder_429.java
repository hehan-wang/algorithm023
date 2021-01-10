package com.david.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NLevelOrder_429 {
    public static void main(String[] args) {
        NTreeNode root = new NTreeNode(1,
                List.of(new NTreeNode(3,
                        List.of(new NTreeNode(5), new NTreeNode(6))
                ), new NTreeNode(2), new NTreeNode(4)));
//        List<List<Integer>> list = new Solution1().levelOrder(root);
        List<List<Integer>> list = new Solution().levelOrder(root);
        System.out.println(list);
    }

    /**
     * 使用队列循环
     */
    static class Solution {
        public List<List<Integer>> levelOrder(NTreeNode root) {
            List<List<Integer>> list = new ArrayList<>();
            if (root == null) return list;
            LinkedList<NTreeNode> queue = new LinkedList<>();
            queue.add(root);
            //外层标识层数
            while (!queue.isEmpty()) {
                //内层填充每一层为上一层入栈的个数
                ArrayList<Integer> l = new ArrayList<>();
                int count = queue.size();
                while ((count--) > 0) {
                    NTreeNode curr = queue.pop();//把上层入队列的数量拿出来放入结果list
                    l.add(curr.val);
                    queue.addAll(curr.children);//把每个节点孩子入队列
                }
                list.add(l);
            }
            return list;
        }
    }

    //使用递归
    static class Solution1 {
        public List<List<Integer>> levelOrder(NTreeNode root) {
            List<List<Integer>> list = new ArrayList<>();
            if (root == null) return list;
            order(list, root, 1);
            return list;
        }

        private void order(List<List<Integer>> list, NTreeNode curr, int level) {
            if (list.size() < level) {//增加一层
                list.add(new ArrayList<>());
            }
            //增加父节点
            list.get(level - 1).add(curr.val);
            //增加子节点
            for (NTreeNode child : curr.children) {
                order(list, child, level + 1);
            }
        }
    }
}
