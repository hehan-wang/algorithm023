package com.david.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

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
        int[] arr = {3, 2, 1};
        int[] res = new Solution().getLeastNumbers(arr, 2);
        System.out.println(Arrays.toString(res));
    }

    //使用最小堆
    static class Solution {
        public int[] getLeastNumbers(int[] arr, int k) {
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            for (int i : arr) {
                minHeap.add(i);
            }
            int[] res = new int[k];
            for (int i = 0; i < k; i++) {
                res[i] = minHeap.poll();
            }
            return res;
        }
    }

}
