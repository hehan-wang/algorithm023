package com.david.leetcode;

public class IsPalindrome_125 {
    public static void main(String[] args) {
        System.out.println(new Solution().isPalindrome("A man, a plan, a canal: Panama"));
    }

    static class Solution {
        public boolean isPalindrome(String s) {
            char[] chars = s.toCharArray();
            int i = 0, j = s.length() - 1;
            while (i < j) {
                while (i < j && !Character.isLetterOrDigit(chars[i])) i++;//先忽略掉非字符数字的
                while (i < j && !Character.isLetterOrDigit(chars[j])) j--;
                if (i < j && Character.toLowerCase(chars[i]) != Character.toLowerCase(chars[j])) {//存在前后不相等的直接返回false
                    return false;
                } else {//否则迭代继续查询
                    i++;
                    j--;
                }
            }
            return true;
        }
    }
}
