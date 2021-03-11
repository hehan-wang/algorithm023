package com.david.leetcode;

public class NumJewelsInStones_771 {
    public static void main(String[] args) {
        System.out.println(new Solution().numJewelsInStones("aA", "aAAbbbb"));
    }

    /**
     * 先把jewels放在缓存数组中 由于ascii码最大255 所以使用int[255]足够
     */
    static class Solution {
        public int numJewelsInStones(String jewels, String stones) {
            boolean[] cache = new boolean[255];
            int res = 0;
            for (char c : jewels.toCharArray()) {
                cache[c] = true;
            }
            for (char c : stones.toCharArray()) {
                if (cache[c]) res++;
            }
            return res;
        }
    }
}
