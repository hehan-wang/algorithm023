package com.david.leetcode;

public class Solve_130 {
    public static void main(String[] args) {
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'},
        };
//        new Solution().solve(board);
        new Solution1().solve(board);
        for (char[] chars : board) {
            for (char c : chars) {
                System.out.printf("%s \t", c);
            }
            System.out.println();
        }
    }


    /**
     * 连通性问题使用并查集
     * 把跟边界O连接的区域放入一个并查集 剩余全是X
     */
    static class Solution1 {
        static class UnionFind {
            int[] parent;

            public UnionFind(int n) {
                parent = new int[n + 1];
                for (int i = 0; i <= n; i++) {
                    parent[i] = i;
                }
            }

            public void union(int i, int j) {
                int rootI = find(i);
                int rootJ = find(j);
                if (rootI != rootJ) {
                    parent[rootI] = rootJ;
                }
            }

            public int find(int i) {
                while (i != parent[i]) {//到顶了
                    parent[i] = parent[parent[i]];
                    i = parent[i];
                }
                return i;
            }

            public boolean isConnected(int i, int j) {
                return find(i) == find(j);
            }
        }


        int rows;
        int cols;

        public void solve(char[][] board) {
            if (board == null || board.length == 0) return;

            rows = board.length;
            cols = board[0].length;

            // 用一个虚拟节点, 边界上的O 的父节点都是这个虚拟节点
            UnionFind uf = new UnionFind(rows * cols + 1);
            int dummyNode = rows * cols;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (board[i][j] == 'O') {
                        // 遇到O进行并查集操作合并
                        if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
                            // 边界上的O,把它和dummyNode 合并成一个连通区域.
                            uf.union(node(i, j), dummyNode);
                        } else {
                            // 和上下左右合并成一个连通区域.
                            if (i > 0 && board[i - 1][j] == 'O')
                                uf.union(node(i, j), node(i - 1, j));
                            if (i < rows - 1 && board[i + 1][j] == 'O')
                                uf.union(node(i, j), node(i + 1, j));
                            if (j > 0 && board[i][j - 1] == 'O')
                                uf.union(node(i, j), node(i, j - 1));
                            if (j < cols - 1 && board[i][j + 1] == 'O')
                                uf.union(node(i, j), node(i, j + 1));
                        }
                    }
                }
            }

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (uf.isConnected(node(i, j), dummyNode)) {
                        // 和dummyNode 在一个连通区域的,那么就是O；
                        board[i][j] = 'O';
                    } else {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        int node(int i, int j) {
            return i * cols + j;
        }
    }


    /**
     * dfs
     * 问题转化成求跟边界相邻的O
     * 1.先把跟边界相邻O替换成#(没被包围 而且走过了)
     * 2.把剩余O替换成X(被包围了)
     * 3.把#替换成O
     * time:O(2mn)
     * space:O(1)
     */
    static class Solution {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        public void solve(char[][] board) {
            if (board == null || board.length == 0 || board[0].length == 0) return;
            int m = board.length;
            int n = board[0].length;
            //1.找出跟边界相邻的O(没被包围) 标记成#
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    boolean isEdge = i == 0 || j == 0 || i == m - 1 || j == n - 1;
                    if (isEdge && board[i][j] == 'O') {
                        dfsMarking(board, i, j);
                    }
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'O') board[i][j] = 'X';//2.把被包围的O改成X
                    if (board[i][j] == '#') board[i][j] = 'O';//3.还原O
                }
            }
        }

        private void dfsMarking(char[][] board, int i, int j) {
            if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == 'X' || board[i][j] == '#')
                return;
            for (int d = 0; d < dx.length; d++) {
                board[i][j] = '#';
                dfsMarking(board, i + dx[d], j + dy[d]);
            }
        }
    }
}
