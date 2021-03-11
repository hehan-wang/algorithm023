package com.david.leetcode;

public class ReverseStr_541 {
    public static void main(String[] args) {
        System.out.println(new Solution().reverseStr("abcdefg", 2));
    }

    /**
     * 外层循环每次移动2k个元素
     * 里层循环使用双指针 reverse(i,i+k-1) 当长度不足length的时候取reverse(i,length)
     */
    static class Solution {
        public String reverseStr(String s, int k) {
            char[] chars = s.toCharArray();
            for (int start = 0; start < chars.length; start += (k << 1)) {
                for (int i = start, j = Math.min(start + k - 1, chars.length - 1); i < j; i++, j--) {
                    char c = chars[i];
                    chars[i] = chars[j];
                    chars[j] = c;
                }
            }
            return new String(chars);
        }
    }
}
