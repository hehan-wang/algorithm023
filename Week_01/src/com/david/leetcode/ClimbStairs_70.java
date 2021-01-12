package com.david.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 爬楼梯本质上就是斐波那契数列
 * f(n)=f(n-1)+f(n-2)
 */
public class ClimbStairs_70 {
    public static void main(String[] args) {
//        int count = new Solution().climbStairs(4);
        int count = new Solution1().climbStairs(4);
        System.out.println(count);
    }

    /**
     * 使用一次循环两个变量解决
     * time:O(n)
     * space:O(1)
     */
    static class Solution1 {
        public int climbStairs(int n) {
            if (n <= 2) return n;//等于1/2直接返回1/2
            int a = 1, b = 2, c = 3;//初始化
            for (int i = 3; i < n; i++) {
                a = b;//迭代n-2
                b = c;//迭代n-1
                c = a + b;//迭代n
            }

            return c;
        }
    }

    //使用递归+缓存
    static class Solution {
        int[] cache;

        public int climbStairs(int n) {
            if (cache == null) {//第一次进入递归初始化数组
                cache = new int[n + 1];
            }
            if (cache[n] != 0) {
                return cache[n];
            } else {
                return cache[n] = rec(n);
            }

        }

        public int rec(int n) {
            if (n <= 2) return n;// n=1 有一种解法 n=2有两种解法
            return climbStairs(n - 1) + climbStairs(n - 2);//每种解法可以拆解成(n-1)的解法个数 和(n-2)的解法个数的和
        }
    }
}
