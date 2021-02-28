package com.david.leetcode;

public class CountBits_338 {
    public static void main(String[] args) {
//        int[] res = new Solution().countBits(5);
        int[] res = new Solution1().countBits(5);
        for (int n : res) {
            System.out.println(n);
        }
    }


    /**
     * dp解法
     * 奇数1的个数比前一个偶数多1
     * 偶数1的个数跟 i/2的偶数一致
     * time:O(n)
     * space:O(n)
     */
    static class Solution1 {
        public int[] countBits(int num) {
            int[] res = new int[num + 1];
            res[0] = 0;
            for (int i = 1; i <= num; i++) {
                if ((i & 1) == 1) res[i] = res[i - 1] + 1;
                else res[i] = res[i >> 1];
            }
            return res;
        }
    }

    /**
     * 暴力法
     * time:O(num*length) ~ O(n^2/2)
     * space:O(n)
     */
    static class Solution {
        public int[] countBits(int num) {
            int[] res = new int[num + 1];
            for (int i = 0; i <= num; i++) {
                int count = 0;
                int n = i;
                while (n != 0) {
                    if ((n & 1) == 1) count++;
                    n >>= 1;
                }
                res[i] = count;
            }
            return res;
        }
    }
}
