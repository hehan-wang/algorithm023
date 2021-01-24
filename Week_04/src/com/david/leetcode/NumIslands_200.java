package com.david.leetcode;

public class NumIslands_200 {
    public static void main(String[] args) {

    }

    /**
     * 使用dfs的思想
     * 1.边界条件控制
     * 2.两层循环检查每个节点
     * 3.检查到陆地的节点置为0 计数 并且dfs该节点上下左右
     * time:O(m*n)
     */
    static class Solution {
        public int numIslands(char[][] grid) {
            int count = 0;
            //边界控制
            if (grid == null || grid.length == 0 || grid[0].length == 0) return count;
            //两层循环
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1') {
                        dfsMarking(grid, i, j);
                        count++;
                    }
                }
            }
            return count;
        }

        //dfs
        private void dfsMarking(char[][] grid, int i, int j) {
            //terminator 下标超出边界或者不是陆地(grid[i][j]!=1)
            if (i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1 || grid[i][j] != '1') return;
            //process 节点置为0
            grid[i][j] = '0';
            //drill down 搜索上下左右
            dfsMarking(grid, i - 1, j);
            dfsMarking(grid, i + 1, j);
            dfsMarking(grid, i, j - 1);
            dfsMarking(grid, i, j + 1);
            //revert states 无
        }

    }
}
