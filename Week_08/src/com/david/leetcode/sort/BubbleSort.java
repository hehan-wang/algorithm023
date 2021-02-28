package com.david.leetcode.sort;

/**
 * 冒泡排序
 * 两次循环 内层每次冒泡出最大的放在最后
 * time:O(n^2)
 */
public class BubbleSort {
    public static void main(String[] args) {
        SortTest.test(BubbleSort::sort);
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length == 0) return;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {//最后一个元素不用排序所以-1
            for (int j = 0; j < n - 1 - i; j++) {//每一轮排序结束比上一轮少一个
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }
}
