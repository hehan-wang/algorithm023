package com.david.leetcode;

public class ReverseWords_557 {
    public static void main(String[] args) {
        System.out.println(new Solution().reverseWords("Let's take LeetCode contest"));
        System.out.println(new Solution1().reverseWords("Let's take LeetCode contest"));
    }

    /**
     * 双指针一次遍历
     */
    static class Solution1 {
        public String reverseWords(String s) {
            StringBuilder ans = new StringBuilder();
            char[] chars = s.toCharArray();
            int n = chars.length;
            for (int index = 0; index < n; ) {
                int begin = index;
                while (index < n && chars[index] != ' ') index++;//找到第一个单词的结尾
                for (int i = index - 1; i >= begin; i--) ans.append(chars[i]);//倒序灌入ans
                while (index < n && chars[index] == ' ') {
                    index++;
                    ans.append(' ');
                }
            }
            return ans.toString();
        }
    }

    /**
     * 1.split
     * 2.reverse
     * 3.join
     */
    static class Solution {
        public String reverseWords(String s) {
            String[] split = s.split(" ");
            for (int i = 0; i < split.length; i++) {
                split[i] = reverse(split[i]);
            }
            return String.join(" ", split);
        }

        public String reverse(String s) {
            char[] chars = s.toCharArray();
            for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
                char c = chars[i];
                chars[i] = chars[j];
                chars[j] = c;
            }
            return new String(chars);
        }
    }
}
