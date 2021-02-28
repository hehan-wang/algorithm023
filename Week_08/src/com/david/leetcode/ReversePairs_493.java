package com.david.leetcode;

/**
 * 思路
 * 1. 双重循环枚举两个元素对 O(n^2)
 * 2. merge sort
 * 3. 树形数组
 */
public class ReversePairs_493 {
    public static void main(String[] args) {
        System.out.println(new Solution().reversePairs(new int[]{1, 3, 2, 3, 1}));
    }

    /**
     * TODO 复习一遍
     * 归并排序
     * 终极玩法 超过面试官水平
     * O(nlogn)
     * 思路：
     * 归并排序
     * i:左侧下标 j:右侧下标
     * num[j]>nums[i]的话存在逆序对 count++
     * i 还有剩余的话(剩余的都是大数)count+=i的个数
     */
    static class Solution {
        public int reversePairs(int[] nums) {
            if (nums == null || nums.length == 0) return 0;
            return mergesort(nums, 0, nums.length - 1);
        }

        private int mergesort(int[] nums, int left, int right) {
            if (left >= right) return 0;
            int mid = left + (right - left) / 2;
            int count = mergesort(nums, left, mid) + mergesort(nums, mid + 1, right);
            int[] tmp = new int[right - left + 1];
            int i = left, c = left, k = 0;//i存左数组 k存cache下标 c存逆序对个数
            for (int j = mid + 1; j <= right; j++) {//j存右数组下标
                while (c <= mid && nums[c] < 2 * (long) nums[j]) c++;
                while (i <= mid && nums[i] < nums[j]) tmp[k++] = nums[i++];
                tmp[k++] = nums[j];
                count += mid - c + 1;//逆序对个数
            }
            while (i <= mid) tmp[k++] = nums[i++];
            System.arraycopy(tmp, 0, nums, left, right - left + 1);
            return count;
        }
    }
}
