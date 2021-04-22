package com.david.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * 剑指 Offer 40. 最小的k个数
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 * <p>
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 * <p>
 * 使用最小堆或者排序
 */
public class GetLeastNumbers_offer40 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().getLeastNumbers(new int[]{3, 2, 1}, 2)));
        System.out.println(Arrays.toString(new Solution1().getLeastNumbers(new int[]{3, 2, 1}, 2)));
        System.out.println(Arrays.toString(new Solution2().getLeastNumbers(new int[]{3, 2, 1}, 2)));
        System.out.println(new SolutionN().GetLeastNumbers_Solution(new int[]{3, 2, 1}, 2));
    }

    /**
     * 使用最小堆
     * O(nlogn)
     */
    static class Solution {
        public int[] getLeastNumbers(int[] arr, int k) {
            if (k == 0 || k > arr.length) return new int[0];
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            for (int i : arr) minHeap.add(i);
            int[] res = new int[k];
            for (int i = 0; i < k; i++) res[i] = minHeap.poll();
            return res;
        }
    }

    /**
     * 使用大根堆每次把最大的删掉
     * O(logk)
     */
    static class Solution1 {
        public int[] getLeastNumbers(int[] arr, int k) {
            if (k == 0 || k > arr.length) return new int[0];
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.<Integer>naturalOrder().reversed());
            for (int i : arr) {
                if (maxHeap.size() < k) {
                    maxHeap.offer(i);
                } else if (i < maxHeap.peek()) {
                    maxHeap.poll();
                    maxHeap.offer(i);
                }
            }
            return maxHeap.stream().mapToInt(e -> e).toArray();
        }
    }

    /**
     * 快排思想 由于每次搜一半 1/2+1/4...+1 ≈2*n
     * 每次partition把小于pivot的放在左边，大于pivot的放在右边。
     * p=k的时候返回左边就行
     * p>k 扫左边 p<k 扫右边
     * O(n)
     */
    static class Solution2 {
        public int[] getLeastNumbers(int[] arr, int k) {
            if (k == 0 || arr.length == 0 || k > arr.length) return new int[0];
            return quickSearch(arr, 0, arr.length - 1, k - 1);
        }

        private int[] quickSearch(int[] nums, int lo, int hi, int k) {
            int p = partition(nums, lo, hi);
            if (p == k) return Arrays.copyOf(nums, p + 1);
            return p > k ? quickSearch(nums, lo, p - 1, k) : quickSearch(nums, p + 1, hi, k);//!!!一定用p 之前用hi lo 导致每次都全列表扫编程n^2
        }

        private int partition(int[] arr, int lo, int hi) {
            int pivot = hi, counter = lo;
            for (int i = lo; i < hi; i++) {
                if (arr[i] < arr[pivot]) swap(arr, i, counter++);
            }
            swap(arr, counter, pivot);
            return counter;
        }

        private void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    //牛客网
    public static class SolutionN {
        public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
            if (k == 0 || input == null || input.length < k) return new ArrayList();
            return quickSearch(input, k - 1, 0, input.length - 1);
        }

        private ArrayList<Integer> quickSearch(int[] input, int k, int lo, int hi) {
            int p = partition(input, lo, hi);
            if (k == p)
                return Arrays.stream(input).boxed().limit(k + 1).collect(Collectors.toCollection(ArrayList::new));
            return k < p ? quickSearch(input, k, lo, p - 1) : quickSearch(input, k, p + 1, hi);
        }

        private int partition(int[] input, int lo, int hi) {
            int pivot = hi, counter = lo;
            for (int i = lo; i < hi; i++) {
                if (input[i] < input[pivot]) swap(input, i, counter++);
            }
            swap(input, counter, pivot);
            return counter;
        }

        private void swap(int[] input, int i, int j) {
            int tmp = input[i];
            input[i] = input[j];
            input[j] = tmp;
        }
    }

}
