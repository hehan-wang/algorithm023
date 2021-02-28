package com.david.leetcode;

public class IsPowerOfTwo_231 {
    public static void main(String[] args) {
        System.out.println(new Solution().isPowerOfTwo(218));
    }

    /**
     * n & (n - 1) 清掉末尾的1
     * 0100 清掉末尾1 一定为0
     * <p>
     * 01000 &
     * 00111
     * =0
     */
    static class Solution1 {
        public boolean isPowerOfTwo(int n) {
            if (n <= 0) return false;
            return (n & (n - 1)) == 0;
        }
    }

    //常规做法  不断除2
    static class Solution {
        public boolean isPowerOfTwo(int n) {
            if (n == 0) return false;
            while (n % 2 == 0) n = n >> 1;
            return n == 1;
        }
    }
}
