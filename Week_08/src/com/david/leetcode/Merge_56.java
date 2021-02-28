package com.david.leetcode;

import java.util.Arrays;

public class Merge_56 {

    /**
     * 思路：
     * 先按左元素排序
     * 遍历intervals 当第一次添加的时候 或 当前左边界 > 结果右边界时候 新开一个数组范围
     * 否则 取最大更新右边界
     * time:O(nlogn) ---> 排序O(nlogn) ，遍历O(n),copy o(n),取最大O(nlogn)
     */
    class Solution {
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
            int[][] res = new int[intervals.length][2];
            int index = -1;//初始值-1为了 判断初始数组为空
            for (int[] interval : intervals) {
                if (index == -1 || interval[0] > res[index][1]) res[++index] = interval;//数组为空 或者当前左边界>结果右边界的时候 --> 需要添加新区域
                else res[index][1] = Math.max(res[index][1], interval[1]);//有重合的情况下取最大右边界更新
            }
            return Arrays.copyOf(res, index + 1);//由于index值不固定 所以copy结果数据到另一个数组中(index是下标+1变成length)
        }

    }
}
