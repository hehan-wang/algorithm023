package com.david.leetcode;

public class Bsearch {
    public static void main(String[] args) {
        int[] a = {1, 3, 6, 22, 45, 13234, 134241};
        System.out.println(binarySearch(a, 22));
    }

    static public int binarySearch(int[] array, int target) {
        int lo = 0, high = array.length - 1;
        while (lo <= high) {
            int mid = lo + (high - lo) >> 1;//防止相加超出上限
            if (array[mid] == target) return mid;
            else if (array[mid] > target) high = mid - 1;
            else lo = mid + 1;
        }
        return -1;
    }
}
