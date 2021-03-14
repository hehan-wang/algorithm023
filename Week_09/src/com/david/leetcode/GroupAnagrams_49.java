package com.david.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GroupAnagrams_49 {
    //异位词排序后是一样的 先排序 在分组
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(s -> {
                char[] chars = s.toCharArray();
                Arrays.sort(chars);
                return new String(chars);
            })).values());
        }
    }
}
