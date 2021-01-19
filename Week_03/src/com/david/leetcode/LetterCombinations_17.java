package com.david.leetcode;

import java.util.*;

public class LetterCombinations_17 {
    public static void main(String[] args) {
        List<String> strings = new Solution().letterCombinations("23");
        System.out.println(strings);
    }

    static class Solution {
        Map<Character, String> map = new HashMap<>();

        {
            map.put('2', "abc");
            map.put('3', "def");
            map.put('4', "ghi");
            map.put('5', "jkl");
            map.put('6', "mno");
            map.put('7', "pqrs");
            map.put('8', "tuv");
            map.put('9', "wxyz");
        }

        public List<String> letterCombinations(String digits) {
            List<String> res = new ArrayList<>();
            if (digits == null | Objects.equals(digits, "")) return res;
            combine(digits, 0, res, "");
            return res;
        }

        private void combine(String digits, int depth, List<String> res, String tmp) {
            //terminator
            if (digits.length() == depth) {
                res.add(tmp);
                return;
            }
            //process
            String letters = map.get(digits.charAt(depth));
            for (int i = 0; i < letters.length(); i++) {
                //drill down
                combine(digits, depth + 1, res, tmp + letters.charAt(i));
            }
            //reverse states

        }
    }
}
