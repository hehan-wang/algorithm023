package com.david.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolveNQueens_51 {
    public static void main(String[] args) {
        List<List<String>> lists = new Solution().solveNQueens(4);
        System.out.println(lists);

    }

    static class Solution {
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> res = new ArrayList<>();
            boolean[] qCol = new boolean[n], qPie = new boolean[2 * n], qNa = new boolean[2 * n];
            char[][] board = new char[n][n];
            for (char[] row : board) {
                Arrays.fill(row, '.');
            }
            dfs(board, res, 0, qCol, qPie, qNa, n);
            return res;
        }

        private void dfs(char[][] board, List<List<String>> res, int row, boolean[] qCol, boolean[] qPie, boolean[] qNa, int n) {
            //terminator
            if (row == n) {
                List<String> snapshot = new ArrayList<>();
                for (char[] chars : board) {
                    snapshot.add(String.valueOf(chars));
                }
                res.add(snapshot);
                return;
            }
            for (int col = 0; col < n; col++) {
                int pie = col - row + n;
                int na = col + row;
                if (qCol[col] || qNa[na] || qPie[pie]) continue;//摆不下跳过
                //process
                qCol[col] = qNa[na] = qPie[pie] = true;
                board[row][col] = 'Q';
                //drill down
                dfs(board, res, row + 1, qCol, qPie, qNa, n);
                //revert states
                qCol[col] = qNa[na] = qPie[pie] = false;
                board[row][col] = '.';
            }
        }
    }
}
