package com.david.leetcode;

public class IsAnagram_242 {
    public static void main(String[] args) {
        System.out.println(new Solution().isAnagram("anagram", "nagaram"));
    }

    //使用数组去重
    static class Solution {
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
}
