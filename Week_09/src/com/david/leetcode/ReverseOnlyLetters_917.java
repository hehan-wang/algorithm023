package com.david.leetcode;

public class ReverseOnlyLetters_917 {
    public static void main(String[] args) {
//        System.out.println(new Solution().reverseOnlyLetters("ab-cd"));
        System.out.println(new Solution().reverseOnlyLetters("Test1ng-Leet=code-Q!"));
        System.out.println(new Solution1().reverseOnlyLetters("Test1ng-Leet=code-Q!"));
    }

    static class Solution1 {
        public String reverseOnlyLetters(String s) {
            if (s == null || s.length() == 0) return s;
            StringBuilder res = new StringBuilder();
            for (int i = 0, j = s.length() - 1; i < s.length(); i++) {
                char c = s.charAt(i);
                if (Character.isLetter(c)) {
                    while (j >= 0 && !Character.isLetter(s.charAt(j))) j--;
                    res.append(s.charAt(j--));
                } else {
                    res.append(c);
                }
            }
            return res.toString();
        }
    }

    /**
     * time:O(n)
     */
    static class Solution {
        public String reverseOnlyLetters(String s) {
            if (s == null || s.length() == 0) return s;
            char[] res = s.toCharArray();
            for (int i = s.length() - 1, index = 0; i >= 0; ) {//index存结果数组下标 i存遍历下标
                if (!Character.isLetter(s.charAt(i))) {//i非字母跳过 只有字母才能填充
                    i--;
                    continue;
                }
                if (!Character.isLetter(res[index])) {//index非字母跳过 只有字母位置才能被填充
                    index++;
                    continue;
                }
                //升下i index 都为字母的情况填充
                res[index++] = s.charAt(i--);
            }
            return new String(res);
        }
    }
}
