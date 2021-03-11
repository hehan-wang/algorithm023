package com.david.leetcode;

import java.util.Arrays;

public class ReverseString_344 {
    public static void main(String[] args) {
        char[] chars = {'h', 'e', 'l', 'l', '0'};
        System.out.println(Arrays.toString(chars));
        new Solution1().reverseString(chars);
        System.out.println(Arrays.toString(chars));
    }

    //for循环比while 更强大 代码更简单
    static class Solution1 {
        public void reverseString(char[] s) {
            for (int i = 0, j = s.length - 1; i < j; i++, j--) {
                char c = s[i];
                s[i] = s[j];
                s[j] = c;
            }
        }
    }

    //双指针法
    static class Solution {
        public void reverseString(char[] s) {
            int i = 0, j = s.length - 1;
            while (i < j) {
                char c = s[i];
                s[i] = s[j];
                s[j] = c;
                i++;
                j--;
            }
        }
    }
}
