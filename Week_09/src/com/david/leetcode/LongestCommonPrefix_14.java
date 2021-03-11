package com.david.leetcode;

/**
 *
 */
public class LongestCommonPrefix_14 {
    public static void main(String[] args) {
        System.out.println(new Solution().longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(new Solution().longestCommonPrefix(new String[]{"ab", "a"}));
    }

    /**
     * 对齐遍历法 以为第一个串为基准
     * flower
     * flow
     * flight
     * time:O(mn) m:第一个字符串长度  n:数组长度
     */
    static class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) return "";
            char[] str0 = strs[0].toCharArray();
            for (int i = 0; i < str0.length; i++) {//遍历第一个字符串
                for (int j = 1; j < strs.length; j++) {//遍历剩余n-1个串 只要有一个串不一致就退出
                    String str = strs[j];
                    if (str.length() == i || str0[i] != str.charAt(i))
                        return str.substring(0, i);//遍历到当前串的末位 或者当前位不相等就退出(没遍历到最短串的末位&&当前位置所有str相同就继续续查找)
                }
            }
            return strs[0];
        }
    }
}
