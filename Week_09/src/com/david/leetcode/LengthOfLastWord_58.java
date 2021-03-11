package com.david.leetcode;

public class LengthOfLastWord_58 {
    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLastWord("Hello World"));
        System.out.println(new Solution1().lengthOfLastWord("Hello World"));
    }

    /**
     * 从后向前遍历
     * 先去掉尾部空格
     * 然后找到下一个空格或者开头
     * time:O(n)
     */
    static class Solution1 {
        public int lengthOfLastWord(String s) {
            char[] chars = s.toCharArray();
            int end = s.length() - 1;
            while (end >= 0 && chars[end] == ' ') end--;//去掉末尾空串
            int begin = end;
            while (begin >= 0 && chars[begin] != ' ') begin--;
            return end - begin;
        }
    }

    /**
     * 使用split
     */
    static class Solution {
        public int lengthOfLastWord(String s) {
            String[] split = s.split(" ");
            if (split.length == 0) return 0;
            return split[split.length - 1].length();
        }
    }
}
