package com.david.leetcode;

public class TotalNQueens_52 {
    public static void main(String[] args) {
//        System.out.println(new Solution().totalNQueens(4));
        System.out.println(Integer.toBinaryString(4));
        System.out.println(Integer.toBinaryString(-4));
        System.out.println(4 & -4);
    }

    /**
     * 超 位运算代码模板
     */
    static class Solution2 {
        int count = 0;
        int queens;

        public int totalNQueens(int n) {
            queens = (1 << n) - 1;
            slove(0, 0, 0);
            return count;
        }

        private void slove(int col, int pie, int na) {
            if (col == queens) {//col 每次放入q 全都放入后跟queens相等
                count++;
                return;
            }
            int pos = queens & (~(col | pie | na));//当前q可用的位置
            while (pos != 0) {
                int p = pos & (-pos);//当前q位置
                slove(col | p, (pie | p) << 1, (na | p) >> 1);
                pos &= (pos - 1);//每次打掉最后一个1
            }
        }
    }

    /**
     * 官方题解
     * 位运算
     * 思路
     * 列 撇 捺使用int n位表示
     * n&(-n) 取最后一位1
     * n&(n-1) 去掉最后一位1
     */
    static class Solution1 {
        public int totalNQueens(int n) {
            return solve(n, 0, 0, 0, 0);
        }

        private int solve(int n, int row, int col, int pie, int na) {
            if (row == n) return 1;
            int count = 0;
            int availablePosition = ((1 << n) - 1) & (~(col | pie | na));//可用的位为 (1111) & (列撇捺占用的位置取反)
            while (availablePosition != 0) {
                int q = availablePosition & (-availablePosition);//n&(-n) 取最后一位1 就是当前皇后的位置
                count += solve(n, row + 1, col | q, (pie | q) << 1, (na | q) >> 1); //把当前皇后分别放入列撇捺 撇右移一位 捺左移一位
                availablePosition = availablePosition & (availablePosition - 1);//n&(n-1) 去掉最后一位1
                //由于int 是基本类型 不会带到下层改变 所以不用清除状态
            }
            return count;
        }
    }

    /**
     * 回溯法解决同51
     * 使用 列 撇 捺 三个数组判断能否放入
     */
    static class Solution {
        public int totalNQueens(int n) {
            boolean[] qPie = new boolean[n << 1], qNa = new boolean[n << 1], qCol = new boolean[n];//判重
            return backtrack(qPie, qNa, qCol, 0, n);
        }

        private int backtrack(boolean[] qPie, boolean[] qNa, boolean[] qCol, int row, int n) {
            if (row == n) return 1;//n个全部摆成功返回1
            int count = 0;
            for (int col = 0; col < n; col++) {
                int pie = row - col + n, na = row + col;
                if (qPie[pie] || qNa[na] || qCol[col]) continue;
                qCol[col] = qNa[na] = qPie[pie] = true;
                count += backtrack(qPie, qNa, qCol, row + 1, n);
                qCol[col] = qNa[na] = qPie[pie] = false;
            }
            return count;//一行n个位置全部没摆成功返回0
        }
    }
}
