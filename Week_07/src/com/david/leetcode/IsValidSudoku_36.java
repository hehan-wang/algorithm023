package com.david.leetcode;

import java.util.HashSet;

public class IsValidSudoku_36 {
    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(new Solution1().isValidSudoku(board));
        System.out.println(new Solution().isValidSudoku(board));
    }

    /**
     * 国外大神写法
     * '4' in row 7 is encoded as "(4)7".
     * '4' in column 7 is encoded as "7(4)".
     * '4' in the top-right block is encoded as "0(4)2".
     */
    static class Solution1 {
        public boolean isValidSudoku(char[][] board) {
            HashSet<String> seen = new HashSet<>();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == '.') continue;
                    String b = String.format("(%s)", board[i][j]);
                    if (!seen.add(b + i) || !seen.add(j + b) || !seen.add(i / 3 + b + j / 3))
                        return false;//i/3 j/3为了使块唯一
                }
            }
            return true;
        }
    }

    /**
     * 行，列，块 各用一个boolean二维数组存
     */
    static class Solution {
        public boolean isValidSudoku(char[][] board) {
            int[][] rows = new int[9][9], cols = new int[9][9], blocks = new int[9][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == '.') continue;//为'.'的直接跳过使用卫语句 减少if 缩进
                    //放入元素
                    char num = board[i][j];
                    int blockIndex = (i / 3) * 3 + j / 3;
                    int numIndex = num - '1';
                    rows[i][numIndex]++;
                    cols[j][numIndex]++;
                    blocks[blockIndex][numIndex]++;
                    //检查元素个数大于1 证明之前放过了
                    if (rows[i][numIndex] > 1 || cols[j][numIndex] > 1 || blocks[blockIndex][numIndex] > 1) return false;
                }
            }
            return true;
        }
    }
}
