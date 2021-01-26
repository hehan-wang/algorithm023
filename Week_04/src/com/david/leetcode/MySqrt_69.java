package com.david.leetcode;

public class MySqrt_69 {
    public static void main(String[] args) {
        System.out.println(new Solution().mySqrt(4));

    }

    /**
     * https://leetcode-cn.com/problems/sqrtx/solution/niu-dun-die-dai-fa-by-loafer/
     * 牛顿迭代 x=(x+a/x)/2
     */
    static class Solution1 {
        public int mySqrt(int a) {
            long x = a;
            while (x * x > a) {
                x = (x + a / x) / 2;
            }
            return (int) x;
        }
    }

    //二分法 超出时间限制
    static class Solution {
        public int mySqrt(int x) {
            long left = 0;
            long right = (x >> 1) + 1;//+1为了照顾x=1的情况
            while (left < right) {
                long mid = (left + right + 1) >>> 1;
                if (mid * mid > x) {
                    right = mid - 1;
                } else {
                    left = mid;//向下取整？
                }
            }
            return (int) left;
        }
    }
}
