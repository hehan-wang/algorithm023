package com.david.leetcode.sort;

import java.util.Arrays;

/**
 * 快速排序 重点
 * 分治思想  任意取一个pivot 形成 [小于pivot..,pivot,大于pivot...] 的形式 然后递归每个子分区
 * time:O(nlogn)
 */
public class QuickSort {
    public static void main(String[] args) {
        SortTest.test(QuickSort::sort);
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length == 0) return;
        quicksort(arr, 0, arr.length - 1);
    }

    public static void quicksort(int[] arr, int begin, int end) {
        if (begin >= end) return;
        int pivot = partition(arr, begin, end);
        quicksort(arr, begin, pivot - 1);
        quicksort(arr, pivot + 1, end);
    }

    //返回povit 并排序[小于pivot..,pivot,大于pivot...]
    private static int partition(int[] arr, int begin, int end) {
        int pivot = end, counter = begin;//counter存小于pivot的个数 ，pivot存基准的下标
        for (int i = begin; i < end; i++) {
            if (arr[i] < arr[pivot]) {//把小于pivot的交换到前面去
                swap(arr, i, counter++);
            }
        }
        swap(arr, counter, pivot);//pivot移动到中间
        return counter;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
