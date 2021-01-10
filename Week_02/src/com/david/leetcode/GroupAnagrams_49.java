package com.david.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 49. 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * <p>
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 */
public class GroupAnagrams_49 {
    public static void main(String[] args) {
        List<List<String>> l = groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        for (int i = 0; i < l.size(); i++) {
            System.out.println("-----------------------");
            System.out.println(l.get(i));
        }
    }

    /**
     * 对字符串排序分组
     * time: O(nklogk)
     * space: O(n)
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        return new ArrayList<>(Stream.of(strs).collect(Collectors.groupingBy(s -> {
            byte[] bytes = s.getBytes();
            Arrays.sort(bytes);
            return new String(bytes);
        })).values());
    }

    /**
     * 一行版本
     * 使用IntStream组装排序String
     */
    public static List<List<String>> groupAnagrams1(String[] strs) {
        return new ArrayList<>(Stream.of(strs).collect(Collectors.groupingBy(s -> s.chars().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString())).values());
    }
}
