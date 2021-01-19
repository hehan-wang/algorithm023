package com.david.leetcode;

/**
 * TODO 高频
 */
public class MyPow_50 {
    public static void main(String[] args) {
//        System.out.println(new Solution().myPow(2D, 10));
        System.out.println(new Solution1().myPow(2D, 10));
    }

    //暴力法 循环 超时
    static class Solution {
        public double myPow(double x, int n) {
            if (x == 0) return 1D;
            double res = 1D;
            if (n < 0) {
                x = 1 / x;
                n = -n;
            }
            for (int i = 0; i < n; i++) {
                res *= x;
            }
            return res;
        }
    }

    //分治法
    static class Solution1 {
        public double myPow(double x, int n) {
            if (n < 0) {//幂运算负数是除的关系
                x = 1 / x;
                n = -n;
            }
            return fastPow(x, n);
        }

        public double fastPow(double x, int n) {
            //terminator
            if (n == 0) return 1;
            //split n=n/2
            //drill down
            double sub = fastPow(x, n / 2);//求出上一个子问题
            //merge
            return n % 2 == 0 ? sub * sub : sub * sub * x;//n偶数的话sub*sub 基数补一个x
            //revert states
        }

    }

}
