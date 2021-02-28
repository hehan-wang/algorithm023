package com.david.leetcode;

/**
 * 思路
 * 1.循环32次判断每一位 然后每次右移一次
 * 2.n&(n-1) 去掉所有低位0 有多少个1执行多少次
 */
public class HammingWeight_191 {
    public static void main(String[] args) {

    }

    /**
     * 位运算方法 O
     * time:O(1的个数) <= O(32)
     */
    static public class Solution1 {
        public int hammingWeight(int n) {
            int res = 0;
            while (n != 0) {
                res++;
                n &= (n - 1);
            }
            return res;
        }
    }

    /**
     * 循环32方法
     * time O(32)
     */
    static public class Solution {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int res = 0;
            for (int i = 0; i < 32; i++) {
                if ((n & 1) == 1) res++;
                n = n >> 1;
            }
            return res;
        }
    }
}
