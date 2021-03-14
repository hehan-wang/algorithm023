package com.david.leetcode;

public class ValidPalindrome_680 {
    public static void main(String[] args) {
        System.out.println(new Solution().validPalindrome("aba"));
        System.out.println(new Solution().validPalindrome("abca"));
    }

    /**
     * 双指针相遇且两边字符都相等 为回文串
     * time:O(n)
     */
    static class Solution {
        public boolean validPalindrome(String s) {
            char[] chars = s.toCharArray();
            int i = 0, j = s.length() - 1;
            for (; i < j && chars[i] == chars[j]; i++, j--) ;
            return i >= j || isValid(chars, i + 1, j) || isValid(chars, i, j - 1);
        }

        private boolean isValid(char[] chars, int i, int j) {
            for (; i < j && chars[i] == chars[j]; i++, j--) ;
            return i >= j;
        }
    }
}
