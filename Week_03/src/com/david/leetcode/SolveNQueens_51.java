package com.david.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SolveNQueens_51 {
    public static void main(String[] args) {
        List<List<String>> lists = new Solution().solveNQueens(4);
        System.out.println(lists);
    }

    /**
     * n皇后经典回溯问题
     * 1.使用三个数组缓存皇后能攻击到 列和对角线位置
     * 2.从第一行开始摆 如果在缓存中都没有一直摆到n行放入结果集。否则回溯清除状态
     */
    static class Solution {
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> res = new ArrayList<>();
            boolean[] qCol = new boolean[n];//皇后能攻击到的列
            boolean[] qDiag1 = new boolean[2 * n];//皇后能攻击到的左对角线 row-col位置
            boolean[] qDiag2 = new boolean[2 * n];//皇后能攻击到的右对角线 row+col位置
            char[][] board = new char[n][n];//存棋盘信息
            for (char[] chars : board) {
                Arrays.fill(chars, '.');
            }
            dfs(board, res, 0, n, qCol, qDiag1, qDiag2);
            return res;
        }

        private void dfs(char[][] board, List<List<String>> res, int row, int n, boolean[] qCol, boolean[] qDiag1, boolean[] qDiag2) {
            //terminator
            if (row == n) {
                ArrayList<String> solution = new ArrayList<>();
                for (char[] chars : board) {
                    solution.add(String.valueOf(chars));
                }
                res.add(solution); //1ms
//                res.add(Arrays.stream(board).map(String::valueOf).collect(Collectors.toList())); //TODO 搞清楚使用lambda慢的原因 使用lambda 4ms，使用原生1ms
                return;
            }
            for (int col = 0; col < n; col++) {//遍历列
                //process
                int diag1 = row + n - col;//存row-col 是因为怕下标越界所以加了n
                int diag2 = row + col;//存row+col
                if (qCol[col] || qDiag1[diag1] || qDiag2[diag2]) {//如果放不进去往下走
                    continue;
                }
                qCol[col] = true;
                qDiag1[diag1] = true;
                qDiag2[diag2] = true;
                board[row][col] = 'Q';
                //drill down
                dfs(board, res, row + 1, n, qCol, qDiag1, qDiag2);
                //revert states
                qCol[col] = false;
                qDiag1[diag1] = false;
                qDiag2[diag2] = false;
                board[row][col] = '.';
            }
        }
    }
}
