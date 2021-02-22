package com.david.leetcode;

public class SolveSudoku_37 {
    public static void main(String[] args) {

    }

    /**
     * 思路：
     * 使用 行，列，块 存储 访问过的格子
     * 1.先遍历一次棋盘 初始棋盘状态
     * 2.81个格子每个依次填1-9数字
     */
    static class Solution {

        public void solveSudoku(char[][] board) {
            if (board == null || board.length != 9 || board[0].length != 9) return;//corner cases
            boolean[][] row = new boolean[9][9], col = new boolean[9][9], box = new boolean[9][9];
            for (int i = 0; i < 9; i++) {//初始化棋盘状态
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == '.') continue;
                    int k = (i / 3) * 3 + j / 3, num = board[i][j] - '1';
                    row[i][num] = col[j][num] = box[k][num] = true;
                }
            }
            helper(0, board, row, col, box);
        }

        private boolean helper(int n, char[][] board, boolean[][] row, boolean[][] col, boolean[][] box) {
            if (n == 81) return true;
            int i = n / 9, j = n % 9;
            if (board[i][j] != '.') return helper(n + 1, board, row, col, box);//不用填数 跳过
            int k = (i / 3) * 3 + j / 3;
            for (int num = 0; num < 9; num++) {
                if (row[i][num] || col[j][num] || box[k][num]) continue;//当前数不能填跳过
                board[i][j] = (char) (num + '1');
                row[i][num] = col[j][num] = box[k][num] = true;
                if (helper(n + 1, board, row, col, box)) return true;
                row[i][num] = col[j][num] = box[k][num] = false;//revert states
            }
            board[i][j] = '.';
            return false;
        }
    }
}
