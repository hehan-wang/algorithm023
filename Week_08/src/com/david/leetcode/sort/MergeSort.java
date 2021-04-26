package com.david.leetcode.sort;

/**
 * 归并排序 重点
 * 分治思想 ，每次一分为二 ，子数组排序 ，在合并两个子数组
 * time:O(nlogn)
 */
public class MergeSort {
    public static void main(String[] args) {
//        SortTest.test(MergeSort::sort);
//        SortTest.test(arr -> new Solution().MySort(arr));
        System.out.println("abc" == "abc");
        System.out.println(new String("abc") == "abc");

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


    public static class Solution {
        /**
         * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
         * 将给定数组排序
         *
         * @param arr int整型一维数组 待排序的数组
         * @return int整型一维数组
         */
        public int[] MySort(int[] arr) {
            // write code here
            if (arr == null || arr.length == 0) return arr;
            mergeSort(arr, 0, arr.length - 1);
            return arr;
        }

        public void mergeSort(int[] arr, int lo, int hi) {
            if (lo >= hi) return;
            int mid = (hi + lo) >> 1;
            mergeSort(arr, lo, mid);
            mergeSort(arr, mid + 1, hi);
            merge(arr, lo, mid, hi);
        }

        public void merge(int[] arr, int lo, int mid, int hi) {
            int i = lo, j = mid + 1, k = 0;
            int[] tmp = new int[hi - lo + 1];
            while (i <= mid && j <= hi) {
                if (arr[i] <= arr[j]) tmp[k++] = arr[i++];
                else tmp[k++] = arr[j++];
            }
            while (j <= hi) tmp[k++] = arr[j++];
            while (i <= mid) tmp[k++] = arr[i++];

            System.arraycopy(tmp, 0, arr, lo, tmp.length);
        }
    }
}
