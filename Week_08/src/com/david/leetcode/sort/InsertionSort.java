package com.david.leetcode.sort;

/**
 * 插入排序
 * 两次循环
 * 每次遍历剩余数组的元素插入到前面有序数组的对应位置上
 * time:O(n^2)
 */
public class InsertionSort {
    public static void main(String[] args) {
        SortTest.test(InsertionSort::sort);
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length == 0) return;
        int n = arr.length;
        for (int i = 1; i < n; i++) {//1开始的话才可以比较
            int preIndex = i - 1;//前面有序数组的下标
            int curr = arr[i];//当前要插入的元素
            while (preIndex >= 0 && arr[preIndex] > curr) {//当前元素比前面小的时候 需要依次挪动数组
                arr[preIndex + 1] = arr[preIndex];//preIndex+1 就是i 第一次相当于arr[i]=arr[i-1]
                preIndex--;
            }
            arr[preIndex + 1] = curr; //最后插入当前元素 preIndex+1位置为插入位置
        }
    }
}
