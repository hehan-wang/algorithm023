package com.david.leetcode;

public class ReverseBits_190 {
    public static void main(String[] args) {

    }

    /**
     * 取模最后一位
     */
    public class Solution {
        // you need treat n as an unsigned value
        public int reverseBits(int n) {
            int res = 0;
            for (int i = 0; i < 32; i++) {
                res = (res << 1) + (n & 1);//res=res*2+n的最后一位
                n >>= 1;
            }
            return res;
        }
    }

}
