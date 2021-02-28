package com.david.leetcode.sort;

/**
 * 归并排序 重点
 * 分治思想 ，每次一分为二 ，子数组排序 ，在合并两个子数组
 * time:O(nlogn)
 */
public class MergeSort {
    public static void main(String[] args) {
        SortTest.test(MergeSort::sort);
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length == 0) return;
        mergesort(arr, 0, arr.length - 1);
    }

    private static void mergesort(int[] arr, int begin, int end) {
        if (begin >= end) return;
        int mid = (begin + end) >> 1;
        mergesort(arr, begin, mid);
        mergesort(arr, mid + 1, end);
        merge(arr, begin, mid, end);
    }

    //合并两个有序数组
    private static void merge(int[] arr, int begin, int mid, int end) {
        int[] tmp = new int[end - begin + 1];
        int i = begin, j = mid + 1, k = 0;
        //三个while合并两个有序数组
        while (i <= mid && j <= end) {//取两个数组取小的放入tmp
            if (arr[i] <= arr[j]) tmp[k++] = arr[i++];
            else tmp[k++] = arr[j++];
        }
        while (j <= end) tmp[k++] = arr[j++];//后数组有剩余元素
        while (i <= mid) tmp[k++] = arr[i++];//或者前数组有剩余元素
        System.arraycopy(tmp, 0, arr, begin, tmp.length);//复制tmp到原数组中
    }
}
