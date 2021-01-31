package com.david.leetcode;

public class SearchMatrix_74 {
    public static void main(String[] args) {
        int[][] m = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        System.out.println(new Solution().searchMatrix(m, 3));
    }

    /**
     * 使用二分查找
     * 有序就用二分查找！！！
     * 由于二维数组全局有序可以转化为类似一维数组解
     */
    static class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length;
            int n = matrix[0].length;
            int high = m * n - 1;//算出总数
            int low = 0;
            while (low <= high) {
                int mid = (high + low) / 2;
                int currM = mid / n;//算出第几行
                int currN = mid % n;//算出第几列
                int curr = matrix[currM][currN];
                if (curr == target) return true;
                else if (curr > target) high = mid - 1;
                else if (curr < target) low = mid + 1;
            }
            return false;
        }
    }

}
