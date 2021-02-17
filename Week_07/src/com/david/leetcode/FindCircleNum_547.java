package com.david.leetcode;

import java.util.HashMap;
import java.util.Map;

public class FindCircleNum_547 {
    public static void main(String[] args) {
        int[][] nums = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1},
        };
        System.out.println(new Solution().findCircleNum(nums));
        System.out.println(new Solution1().findCircleNum(nums));
    }

    static class UnionFind {
        private Map<Integer, Integer> father;
        public int count = 0;

        public UnionFind() {
            count = 0;
            father = new HashMap<>();
        }

        public void add(int x) {
            if (!father.containsKey(x)) {
                father.put(x, null);
                count++;
            }
        }

        public void merge(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                father.put(rootY, rootX);
                count--;
            }
        }

        public int find(int x) {
            int root = x;
            while (father.get(root) != null) {//找到根节点
                root = father.get(root);
            }
            //路径压缩
            while (root != x) {
                Integer originFather = father.get(x);
                father.put(x, root);
                x = originFather;
            }

            return root;
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }

        public int getNumOfSets() {
            return count;
        }
    }

    static class Solution1 {
        public int findCircleNum(int[][] isConnected) {
            UnionFind unionFind = new UnionFind();
            //j<i遍历下三角形
            for (int i = 0; i < isConnected.length; i++) {
                unionFind.add(i);
                for (int j = 0; j < i; j++) {
                    if (isConnected[i][j] == 1) {
                        unionFind.merge(i, j);
                    }
                }
            }
            return unionFind.getNumOfSets();
        }
    }

    /**
     * dfs 同岛屿问题
     * time:O(n^2)
     * space:O(n)
     */
    static class Solution {
        public int findCircleNum(int[][] isConnected) {
            int provinces = isConnected.length;
            boolean[] visited = new boolean[provinces];//一共n个节点
            int count = 0;
            for (int i = 0; i < provinces; i++) {//遍历行
                if (!visited[i]) {
                    dfs(i, isConnected, provinces, visited);
                    count++;
                }
            }
            return count;
        }

        private void dfs(int i, int[][] isConnected, int provinces, boolean[] visited) {
            for (int j = 0; j < provinces; j++) {//遍历列
                if (isConnected[i][j] == 1 && !visited[j]) {
                    visited[j] = true;
                    dfs(j, isConnected, provinces, visited);
                }
            }
        }
    }
}
