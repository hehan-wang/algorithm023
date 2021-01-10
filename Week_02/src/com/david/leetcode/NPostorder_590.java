package com.david.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * N叉树前序遍历
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/solution/ncha-shu-de-hou-xu-bian-li-javade-liang-chong-zuo-/
 */
public class NPostorder_590 {
    public static void main(String[] args) {
        NTreeNode root = new NTreeNode(1,
                List.of(new NTreeNode(3,
                        List.of(new NTreeNode(5), new NTreeNode(6))
                ), new NTreeNode(2), new NTreeNode(4)));
        List<Integer> list = new Solution().postorder(root);
        System.out.println(list);
    }


    //使用递归实现
    static class Solution {
        public List<Integer> postorder(NTreeNode root) {
            List<Integer> list = new ArrayList<>();
            order(list, root);
            return list;
        }

        private void order(List<Integer> list, NTreeNode curr) {
            if (curr == null) return;
            if (curr.children != null && curr.children.size() > 0) {
                for (NTreeNode child : curr.children) {
                    order(list, child);
                }
            }
            list.add(curr.val);
        }

    }
}
