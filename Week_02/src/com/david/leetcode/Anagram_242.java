package com.david.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * <p>
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 * <p>
 * <p>
 * 1.clarification
 * 异位词就是两个词有相同的字符个数 但是位置可能不同
 * <p>
 * 2.possible solutions
 * a 使用map查找 遍历第一个串每一个字符存在 value +1 ，遍历第二个字符没存在一个-1，查找不到字符直接返回不存在。
 * b 排序然后比较字符串
 * <p>
 * 3.coding
 * <p>
 * 4.user case
 */
public class Anagram_242 {
    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram"));
        System.out.println(isAnagram("rat", "car"));
        System.out.println("===================================");
        System.out.println(isAnagram1("anagram", "nagaram"));
        System.out.println(isAnagram1("rat", "car"));

        System.out.println("===================================");
        System.out.println(isAnagram2("anagram", "nagaram"));
        System.out.println(isAnagram2("rat", "car"));
    }

    /**
     * 使用哈希表
     * 1. 判断两个串长度不等直接返回
     * 2. 初始化26个长度数组（因为只有字母 'b'-'a'=1 正好落在数组第二个位置）
     * 3. 遍历 s.length次(两个字符串长度相等)  s负责++ t负责--
     * 4. 遍历26个长度数组 如果都为初始值返回true 否则false
     * https://leetcode-cn.com/problems/valid-anagram/solution/hua-jie-suan-fa-242-you-xiao-de-zi-mu-yi-wei-ci-by/
     * 时间复杂度:O(n)
     * 空间复杂度O(1)
     */
    public static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
            table[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < table.length; i++) if (table[i] != 0) return false;//for/if代码块只有一行可忽略括号{}
        return true;
    }

    /**
     * 使用排序加相等
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     */
    public static boolean isAnagram1(String s, String t) {
        byte[] bs = s.getBytes();
        byte[] bt = t.getBytes();
        Arrays.sort(bs);
        Arrays.sort(bt);
        return Arrays.equals(bs, bt);
    }

    /**
     * 使用map查找
     * 自己实现比较挫
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, AtomicInteger> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.computeIfAbsent(s.charAt(i), k -> new AtomicInteger(0)).addAndGet(1);
        }
        for (int i = 0; i < t.length(); i++) {
            AtomicInteger count = map.get(t.charAt(i));
            if (count == null || (count.decrementAndGet()) < 0) {//有一个字符不存在 或者减没了直接返回false
                return false;
            }
            if (count.get() == 0) {
                map.remove(t.charAt(i));
            }
        }
        System.out.println(map);
        return map.isEmpty();
    }
}
