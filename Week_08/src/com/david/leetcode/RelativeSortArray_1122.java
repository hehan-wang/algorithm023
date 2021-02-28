package com.david.leetcode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 排序方法: 在arr2中按下标顺序排 不在arr2中按大小排序(1000+num)
 */
public class RelativeSortArray_1122 {

    /**
     * 比较器
     * 不在map中+1000 自然就落到后面
     */
    static class Solution1 {
        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            Map<Integer, Integer> cache = IntStream.range(0, arr2.length).boxed().collect(Collectors.toMap(i -> arr2[i], Function.identity()));
            return Arrays.stream(arr1).boxed().sorted(Comparator.comparing(Function.identity(), Comparator.comparingInt(o -> cache.getOrDefault(o, o + 1000)))).mapToInt(Integer::intValue).toArray();
        }
    }

    //计数排序
    static class Solution {
        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            int[] sorted = new int[1001];//因为数组不大于1000
            int[] res = new int[arr1.length];
            int index = 0;
            for (int i : arr1) {//数组1的数量放入缓存
                sorted[i]++;
            }
            for (int j : arr2) {//按顺序遍历数组2放入 按数量放入res
                while (sorted[j]-- > 0) {
                    res[index++] = j;
                }
            }
            for (int i = 0; i < sorted.length; i++) {//把剩余的count>0的元素放入res
                while (sorted[i]-- > 0) {
                    res[index++] = i;
                }
            }
            return res;
        }
    }
}
