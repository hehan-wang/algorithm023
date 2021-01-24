package com.david.leetcode;

public class UpdateBoard_529 {
    public static void main(String[] args) {
        char[][] chars = new char[][]{
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}};
        System.out.println(chars[3][0]);
        System.out.println(chars[1][2]);
        print(chars);
        char[][] res = new Solution().updateBoard(chars, new int[]{1, 0});
        System.out.println();
        print(res);
    }

    private static void print(char[][] chars) {
        for (char[] re : chars) {
            System.out.println();
            for (char c : re) {
                System.out.print(c + "\t");
            }
        }
    }

    /**
     * dfs
     */
    static class Solution {
        //定义8个方向
        int[] dx = {-1, 1, 0, 0, -1, 1, -1, 1};
        int[] dy = {0, 0, -1, 1, -1, 1, 1, -1};

        public char[][] updateBoard(char[][] board, int[] click) {
            int x = click[0];
            int y = click[1];
            if (board[x][y] == 'M') board[x][y] = 'X';//点到雷结束
            else dfs(board, x, y);//点到空地
            return board;
        }

        private void dfs(char[][] board, int x, int y) {
            // x y 周围8格有雷返回
            int cnt = 0;
            for (int i = 0; i < 8; i++) {
                int tx = x + dx[i];
                int ty = y + dy[i];
                if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length) continue; //越界了跳过
                if (board[tx][ty] == 'M') cnt++;
            }
            if (cnt > 0) {
                board[x][y] = (char) (cnt + '0');
                return;
            }
            //x y周围8格没有雷 当前节点设置B dfs 直到有雷出现
            board[x][y] = 'B';
            for (int i = 0; i < 8; i++) {
                int tx = x + dx[i];
                int ty = y + dy[i];
                if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length || board[tx][ty] != 'E')
                    continue;//board[x][y] != 'E'去重
                dfs(board, tx, ty);
            }
        }
    }

}
