package com.david.leetcode.sort;


/**
 * 堆排序 遍历插入小顶堆 然后pop到数组中即为有序
 * 自定义堆方法
 * time:O(nlogn)
 */
public class HeapSortCustom {
    public static void main(String[] args) {
        SortTest.test(HeapSortCustom::sort);
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length == 0) return;
        int n = arr.length;
        for (int i = (n >> 1) - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int tmp = arr[i];
            arr[i] = arr[0];
            arr[0] = tmp;
            heapify(arr, i, 0);
        }
    }

    public static void heapify(int[] arr, int length, int i) {//维护一个大顶堆
        int left = 2 * i + 1, right = 2 * i + 2, largest = i;
        if (left < length && arr[left] > arr[largest]) largest = left;
        if (right < left && arr[right] > arr[largest]) largest = right;
        if (i != largest) {
            int tmp = arr[largest];
            arr[largest] = arr[i];
            arr[i] = tmp;
            heapify(arr, length, largest);
        }
    }
}
