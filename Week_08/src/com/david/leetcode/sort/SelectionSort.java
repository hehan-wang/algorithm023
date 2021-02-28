package com.david.leetcode.sort;

/**
 * 选择排序
 * 两次循环
 * 内层循环 每次取剩余数组中最小的放在前面
 * time:O(n^2)
 */
public class SelectionSort {
    public static void main(String[] args) {
        SortTest.test(SelectionSort::sort);
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length == 0) return;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {//获取剩余数组最小元素的下标
                if (arr[j] < arr[minIndex]) minIndex = j;
            }
            int tmp = arr[minIndex];//交换i和最小元素
            arr[minIndex] = arr[i];
            arr[i] = tmp;
        }
    }
}
