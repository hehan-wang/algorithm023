package com.david.leetcode;

public class LemonadeChange_860 {
    public static void main(String[] args) {
        int[] bills = {5, 5, 5, 10, 20};
        System.out.println(new Solution().lemonadeChange(bills));
    }

    /**
     * 找钱先面值找大的
     */
    static class Solution {
        public boolean lemonadeChange(int[] bills) {
            int n5 = 0, n10 = 0;//5块钱个数和10块钱个数
            for (int bill : bills) {
                switch (bill) {
                    case 5 -> n5++;
                    case 10 -> {
                        if (n5 > 0) {
                            n5--;
                            n10++;
                        } else {
                            return false;
                        }
                    }
                    case 20 -> {
                        if (n10 > 0 && n5 > 0) {
                            n10--;
                            n5--;
                        } else if (n5 > 2) {
                            n5 -= 3;
                        } else {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }
}
