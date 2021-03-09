package com.david.leetcode;

public class MinCostClimbingStairs_746 {
    public static void main(String[] args) {

    }

    //TODO
    static class Solution {
        public int minCostClimbingStairs(int[] cost) {
            int size = cost.length;
            int[] minCost = new int[size];
            minCost[0] = 0;
            minCost[1] = Math.min(cost[0], cost[1]);
            for (int i = 2; i < size; i++) {
                minCost[i] = Math.min(minCost[i - 1] + cost[i], minCost[i - 2] + cost[i - 1]);
            }
            return minCost[size - 1];
        }
    }
}
