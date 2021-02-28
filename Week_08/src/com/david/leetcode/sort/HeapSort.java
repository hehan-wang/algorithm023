package com.david.leetcode.sort;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 堆排序 遍历插入小顶堆 然后pop到数组中即为有序
 * 使用系统pq
 * time:O(nlogn)
 */
public class HeapSort {
    public static void main(String[] args) {
        SortTest.test(HeapSort::sort);
    }

    public static void sort(int[] arr) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.naturalOrder());
        for (int i : arr) {
            minHeap.offer(i);
        }
        int s = minHeap.size();
        for (int i = 0; i < s; i++) {
            arr[i] = minHeap.poll();
        }
    }
}
