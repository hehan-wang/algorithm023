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
        System.out.println(new Solution1().reversePairs(new int[]{1, 3, 2, 3, 1}));
    }

    //归并和统计分开写法
    static class Solution1 {
        public int reversePairs(int[] nums) {
            if (nums.length == 0) return 0;
            return mergeSort(nums, 0, nums.length - 1);
        }

        public int mergeSort(int[] nums, int left, int right) {
            if (left == right) return 0;
            int mid = (left + right) / 2;
            int ret = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);

            /**
             * 首先统计下标对的数量
             * i不动 扫描j直到
             * 由于两边都是升序的
             * 当nums[i]<=2*nums[j] 此时j-m+1 为该i的逆序对
             */
            int i = left, j = mid + 1;
            while (i++ <= mid) {
                while (j <= right && (long) nums[i] > 2 * (long) nums[j]) j++;
                ret += j - mid - 1;
            }

            // 随后合并两个排序数组
            merge(nums, left, mid, right);
            return ret;
        }

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
            if (nums == null || nums.length == 0) return 0;
            return mergesort(nums, 0, nums.length - 1);
        }

        private int mergesort(int[] nums, int left, int right) {
            if (left >= right) return 0;
            int mid = left + (right - left) / 2;
            int count = mergesort(nums, left, mid) + mergesort(nums, mid + 1, right);
            int[] tmp = new int[right - left + 1];
            int i = left, j = mid + 1, c = left, k = 0;//i存左数组 j存右数组 k存cache下标 c存逆序对个数
            while (j <= right) {//先遍历右侧
                while (c <= mid && nums[c] <= 2 * (long) nums[j])
                    c++;//num[c]>2*num[j] 存在逆序c不变 不存在c++  TODO 这块O(n^2) 整体达不到nlog(n)吧?
                while (i <= mid && nums[i] < nums[j]) tmp[k++] = nums[i++];//左侧比num[j]小数先放入res
                tmp[k++] = nums[j++];//放入num[j]
                count += mid - c + 1;//逆序对个数=左侧剩余的数量=总数-非逆序的数量+1
            }
            while (i <= mid) tmp[k++] = nums[i++];//在遍历左侧剩余
            System.arraycopy(tmp, 0, nums, left, right - left + 1);
            return count;
        }
    }
}
