package com.david.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAnagrams_438 {
    public static void main(String[] args) {
        System.out.println(new Solution().findAnagrams("cbaebabacd", "abc"));
    }

    /*
     *滑动窗口
     * 1.把p的下标存入cache数组
     * 2.滑动窗口判断
     * time:O(26*n)
     */
    static class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            int window = p.length();
            ArrayList<Integer> res = new ArrayList<>();
            if (s.length() < window) return res;
            int[] cache = new int[26];
            for (char c : p.toCharArray()) {
                cache[c - 'a']++;
            }
            char[] cs = s.toCharArray();
            for (int i = 0; i < window; i++) {
                cache[cs[i] - 'a']--;
                boolean find = Arrays.stream(cache).allMatch(a -> a == 0);
                if (find) res.add(0);
            }
            for (int i = window; i < s.length(); i++) {
                cache[cs[i - window] - 'a']++;
                cache[cs[i] - 'a']--;
                boolean find = Arrays.stream(cache).allMatch(a -> a == 0);
                if (find) res.add(i - window + 1);
            }
            return res;
        }
    }
}
