package com.david.leetcode;

public class ToLowerCase_709 {
    public static void main(String[] args) {
        System.out.println(new Solution().toLowerCase("Hello"));
    }

    /**
     * ASCII码
     * a-z 97-122
     * A-Z 65-90
     * 0-9 48-57
     * 小写比大写多32
     */
    static class Solution {
        public String toLowerCase(String str) {
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] >= 'A' && chars[i] <= 'Z') chars[i] = (char) (chars[i] + 32);
            }
            return new String(chars);
        }
    }
}
