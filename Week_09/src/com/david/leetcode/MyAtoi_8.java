package com.david.leetcode;

public class MyAtoi_8 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "2147483646";
        int res = solution.myAtoi(str);
        System.out.println(res);

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

    }

    static class Solution {
        public int myAtoi(String s) {
            int n = s.length(), index = 0;
            char[] chars = s.toCharArray();
            //1.移除掉前缀空格 如果全是空格返回0
            while (index < n && chars[index] == ' ') index++;
            if (index == n) return 0;
            //2.判断正负标志位
            int sign = 1;
            if (chars[index] == '+') {
                index++;
            } else if (chars[index] == '-') {
                sign = -1;
                index++;
            }
            //3.转换正常数字 遇到非数字break 超过MAX 截断
            int res = 0;
            while (index < n) {
                char c = chars[index];
                if (c > '9' || c < '0') break;//非数字跳出
                //判断res*10+(c-'0')会不会超过最大值 先判断个位之前 在判断个位
                if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (c - '0') > Integer.MAX_VALUE % 10))
                    return Integer.MAX_VALUE;
                if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && (c - '0') > -(Integer.MIN_VALUE % 10)))
                    return Integer.MIN_VALUE;
                res = res * 10 + sign * (c - '0');
                index++;
            }
            return res;
        }
    }
}
