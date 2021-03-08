package com.david.leetcode;

import java.util.Arrays;

/**
 * 思路
 * 1. 双重循环枚举两个元素对 O(n^2)
 * 2. merge sort
 * 3. 树形数组
 */
public class ReversePairs_493_1 {
    public static void main(String[] args) {
        System.out.println(new Solution().reversePairs(new int[]{1, 3, 2, 3, 1}));
        System.out.println(new Solution1().reversePairs(new int[]{1, 3, 2, 3, 1}));
    }

    //归并和统计分开写法
    static class Solution1 {
        public int reversePairs(int[] nums) {
            if (nums == null || nums.length == 0) return 0;
            return mergeSort(nums, 0, nums.length - 1);
        }

        private int mergeSort(int[] nums, int l, int r) {
            if (l >= r) return 0;
            int mid = (l + r) / 2;
            int count = mergeSort(nums, l, mid) + mergeSort(nums, mid + 1, r);
            //统计count
            int i = l, j = mid + 1;
            while (j++ <= r) {
                while (i <= mid && nums[i] <= 2 * nums[j]) i++; //不是逆序对
                count += mid - i + 1;//mid-i+1即为逆序对个数
            }

//            while (i++ <= mid) {
//                while (j <= r && (long) nums[i] > 2 * (long) nums[j]) j++;
//                count += j - mid - 1;
//            }
            //merge
            merge(nums, l, mid, r);
            return count;
        }

        private void merge(int[] nums, int l, int mid, int r) {
            int i = l, j = mid + 1, k = 0;
            int[] tmp = new int[r - l + 1];
            while (i <= mid && j <= r) {
                if (nums[i] < nums[j]) tmp[k++] = nums[i++];
                else tmp[k++] = nums[j++];
            }
            while (i <= mid) tmp[k++] = nums[i++];
            while (j <= r) tmp[k++] = nums[j++];
            System.arraycopy(tmp, 0, nums, l, r - l + 1);
        }
    }


    /**
     * TODO 复习一遍
     * 归并排序
     * 终极玩法 超过面试官水平
     * O(nlogn)
     * 思路：
     * 归并排序
     * i:左侧下标 j:右侧下标
     * num[j]>2*nums[i]的话存在逆序对 count++
     * i还有剩余的话(剩余的都是大数)count+=i的个数
     */
    static class Solution {
        public int reversePairs(int[] nums) {
            return -1;
        }


    }
}
