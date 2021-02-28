package com.david.leetcode.sort;

import java.util.Arrays;
import java.util.function.Consumer;

public class SortTest {
    //测试模板
    public static void test(Consumer<int[]> sorter) {
        int[] arr = {1, 131231, 2, 13, 345, 345, 13, 464, 67};
        System.out.println(Arrays.toString(arr));
        sorter.accept(arr);
        System.out.println(Arrays.toString(arr));
    }
}
