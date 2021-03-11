package com.david.leetcode;


import java.util.*;
import java.util.stream.Collectors;

public class ReverseWords_151 {
    public static void main(String[] args) {
        System.out.println(new Solution().reverseWords("a good   example"));
        System.out.println(new Solution1().reverseWords("a good   example"));
        System.out.println(new Solution2().reverseWords("a good   example"));
        System.out.println(new Solution3().reverseWords("a good   example"));
//        System.out.println(new Solution().reverseWords("  hello world  "));
//        System.out.println(new Solution1().reverseWords("  hello world  "));
    }

    //练习2
    static class Solution3 {
        public String reverseWords(String s) {
            s = s.trim();
            char[] chars = s.toCharArray();
            int left = s.length() - 1, right = left + 1;
            StringBuilder res = new StringBuilder();
            while (left > 0) {
                if (chars[left] == ' ') {
                    res.append(s.substring(left + 1, right)).append(' ');
                    while (chars[left] == ' ') left--;
                    right = left + 1;
                }
                left--;
            }
            return res.append(s.substring(0, right)).toString();
        }
    }

    /**
     * 双指针法
     * 1.trim
     * 2.从后向前遍历 遇到空格append单词到sb 遇到连续空格--
     * 3.返回sb
     */
    static class Solution2 {
        public String reverseWords(String s) {
            s = s.trim();
            int left = s.length() - 1, right = s.length();//left right 存最后一个字母的下标
            StringBuilder sb = new StringBuilder();
            while (left > 0) {
                if (s.charAt(left) == ' ') {  //遇到' '存sb
                    sb.append(s.substring(left + 1, right)).append(' ');
                    while (s.charAt(left) == ' ') left--;//移除连续' '
                    right = left + 1;
                }
                left--;
            }
            return sb.append(s.substring(0, right)).toString();
        }
    }

    /**
     * 使用双端队列
     * 1.trim
     * 2.遍历s 遇到空格把整个单词插到队列头
     * 3.最后在插一次
     * 4.string.join
     * time:O(2n)
     */
    static class Solution1 {
        public String reverseWords(String s) {
            int left = 0, right = s.length() - 1;
            char[] chars = s.toCharArray();
            while (left <= right && chars[left] == ' ') left++;
            while (left <= right && chars[right] == ' ') right--;
            Deque<String> deque = new LinkedList<>();
            StringBuilder sb = new StringBuilder();
            while (left <= right) {
                if (sb.length() != 0 && chars[left] == ' ') {
                    deque.offerFirst(sb.toString());
                    sb.setLength(0);
                } else if (chars[left] != ' ') {
                    sb.append(chars[left]);
                }
                left++;
            }
            deque.offerFirst(sb.toString());
            return String.join(" ", deque);
        }
    }

    //使用jdk api
    static class Solution {
        public String reverseWords(String s) {
            String[] split = s.trim().split(" +");
            Collections.reverse(Arrays.asList(split));
            return String.join(" ", split);
        }
    }
}
