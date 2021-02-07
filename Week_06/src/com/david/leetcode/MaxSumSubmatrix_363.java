package com.david.leetcode;

public class MaxSumSubmatrix_363 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxSumSubmatrix(new int[][]{{1, 0, 1}, {0, -2, 3}}, 2));
        System.out.println(new Solution1().maxSumSubmatrix(new int[][]{{1, 0, 1}, {0, -2, 3}}, 2));
    }

    /**
     * 思路：
     * 定义矩阵左右边界、 取出每行和的数组 矩阵的最大值即为相邻行的和的最大值。问题转化为求 不大于k的子数组最大和问题。
     * <p>
     * time:O(m2n)
     * space:O(n)
     */
    static class Solution1 {
        public int maxSumSubmatrix(int[][] matrix, int k) {
            int rows = matrix.length, cols = matrix[0].length, max = Integer.MIN_VALUE;
            for (int l = 0; l < cols; l++) {//左边界
                int[] rowNum = new int[rows];
                for (int r = l; r < cols; r++) {//右边界
                    for (int i = 0; i < rows; i++) {//把每一行加和
                        rowNum[i] += matrix[i][r];
                    }
                    max = Math.max(max, dpMax(rowNum, k));//取每个rowNum的和的最大值
                }
            }
            return max;
        }

        //先使用正增益取最大 取不到使用暴力法
        private int dpMax(int[] arr, int k) {
            //正增益法
            int sum = arr[0], max = sum;
            for (int i = 1; i < arr.length; i++) {
                if (sum > 0) sum += arr[i];
                else sum = arr[i];
                max = Math.max(sum, max);
                if (max == k) return max;//卫语句 减少循环
            }
            if (max <= k) return max;
            //暴力法
            max = Integer.MIN_VALUE;
            for (int l = 0; l < arr.length; l++) {
                sum = 0;
                for (int r = l; r < arr.length; r++) {
                    sum += arr[r];
                    if (sum <= k && sum > max) max = sum;
                    if (max == k) return max;//卫语句 减少循环
                }
            }
            return max;
        }
    }

    /**
     * 暴力法
     * dp方程 dp[i][j]=dp[i][j-1]+dp[i-1][j]+dp[i-1][j-1]+matrix[i-1][j-1]
     * time:O(m2n2)
     * space:O(mn)
     */
    static class Solution {
        public int maxSumSubmatrix(int[][] matrix, int k) {
            int rows = matrix.length, cols = matrix[0].length, max = Integer.MIN_VALUE;
            for (int i1 = 1; i1 <= rows; i1++) {
                for (int j1 = 1; j1 <= cols; j1++) {
                    int[][] dp = new int[rows + 1][cols + 1];//每次更新左上角renew dp数组
                    dp[i1][j1] = matrix[i1 - 1][j1 - 1];
                    for (int i2 = i1; i2 <= rows; i2++) {
                        for (int j2 = j1; j2 <= cols; j2++) {
                            dp[i2][j2] = dp[i2 - 1][j2] + dp[i2][j2 - 1] - dp[i2 - 1][j2 - 1] + matrix[i2 - 1][j2 - 1];
                            if (dp[i2][j2] <= k && dp[i2][j2] > max) max = dp[i2][j2];
                        }
                    }
                }
            }
            return max;
        }
    }
}
