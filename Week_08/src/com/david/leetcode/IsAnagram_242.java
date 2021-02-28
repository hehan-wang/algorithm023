package com.david.leetcode;

import java.util.Arrays;

public class IsAnagram_242 {
    public static void main(String[] args) {
        System.out.println();
    }

    /**
     * 使用缓存数组
     * 最终chars 一定全为0才是异位词 所有字母都成对抵消了
     * O(2*n)
     */
    class Solution1 {
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) return false;
            int[] chars = new int[26];
            for (int i = 0; i < s.length(); i++) {
                chars[s.charAt(i) - 'a']++;
                chars[t.charAt(i) - 'a']--;
            }
            for (int c : chars) {
                if (c != 0) return false;
            }
            return true;
        }
    }

    /**
     * 遇事不决先排序
     * O(nlogn)
     */
    class Solution {
        public boolean isAnagram(String s, String t) {
            byte[] b1 = s.getBytes();
            byte[] b2 = t.getBytes();
            Arrays.sort(b1);
            Arrays.sort(b2);
            return Arrays.equals(b1, b2);
        }
    }
}
