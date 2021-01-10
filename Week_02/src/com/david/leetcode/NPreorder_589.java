package com.david.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * N叉树前序遍历
 */
public class NPreorder_589 {
    public static void main(String[] args) {
        NTreeNode root = new NTreeNode(1,
                List.of(new NTreeNode(3,
                        List.of(new NTreeNode(5), new NTreeNode(6))
                ), new NTreeNode(2), new NTreeNode(4)));
        List<Integer> list = new Solution().preorder(root);
        System.out.println(list);
    }


    //使用递归实现
    static class Solution {
        public List<Integer> preorder(NTreeNode root) {
            List<Integer> res = new ArrayList<>();
            order(res, root);
            return res;
        }

        private void order(List<Integer> res, NTreeNode curr) {
            if (curr == null) return;
            res.add(curr.val);
            if (curr.children != null) {
                for (NTreeNode child : curr.children) {
                    order(res, child);
                }
            }
        }

    }

}
