package com.david.leetcode;

import java.util.*;

/**
 * 239. 滑动窗口最大值
 */
public class MaxSlidingWindow_239 {
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
//        int[] max = new Solution().maxSlidingWindow(nums, 3);
//        int[] max = new Solution1().maxSlidingWindow(nums, 3);
        int[] max = new Solution2().maxSlidingWindow(nums, 3);
        System.out.println(Arrays.toString(max));
    }

    /**
     * 使用deque
     * 保证deque 第一个元素为最大值(1.第一个元素超出窗口剔除 2.删除小于当前值的元素)
     */
    static class Solution2 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k == 0 || nums.length < k) return new int[0];
            int n = nums.length;
            int[] res = new int[n - k + 1];
            Deque<Integer> deque = new LinkedList<>();
            for (int j = 0; j < n; j++) {
                int i = j + 1 - k;
                if (i > 0 && !deque.isEmpty() && deque.peekFirst() == nums[i - 1]) deque.removeFirst();
                while (!deque.isEmpty() && deque.peekLast() < nums[j]) deque.removeLast();
                deque.addLast(nums[j]);
                if (i >= 0) res[i] = deque.peekFirst();
            }
            return res;
        }
    }

    /**
     * 堆优化
     * 只有最大值已经出窗口了才删除
     * time:O(nlogn)插入logn 插入n次
     * space:O(n) 额外空间等于数据长度
     */
    static class Solution1 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || k == 0 || nums.length == 0 || nums.length < k) return new int[0];
            int n = nums.length;
            //先比较大小 在比较下标 的大顶堆
            PriorityQueue<int[]> maxHeap = new PriorityQueue<>((o1, o2) -> o2[0] != o1[0] ? o2[0] - o1[0] : o2[1] - o1[1]);
            int[] res = new int[n - k + 1]; //一共有n-k+1个窗口
            for (int i = 0; i < k; i++) { //先遍历到一个窗口大小
                maxHeap.offer(new int[]{nums[i], i});
            }
            res[0] = maxHeap.peek()[0];//一个窗口最大值产生
            //在遍历窗口到n
            for (int i = k; i < n; i++) {//向右滑动窗口
                maxHeap.offer(new int[]{nums[i], i});
                int start = i - k + 1;//窗口第一个元素下标
                while (maxHeap.peek()[1] < start) {//删除窗口外的堆顶值
                    maxHeap.poll();
                }
                res[start] = maxHeap.peek()[0];//赋值
            }
            return res;
        }
    }

    /**
     * leetcode超时 由于频繁删除
     * 使用大顶堆完成
     * 1.循环数组所有元素
     * 2.当 start=(i-k) >0证明有元素滑出窗口去掉nums[start]
     * 3.当window数量够3个才放入数组
     * time:O(n*2*logk)
     */
    static class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k == 0) return new int[]{};//边界条件
            int n = nums.length;
            int[] res = new int[n - k + 1];//结果数据
            PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);//大顶堆
            for (int i = 0; i < n; i++) {
                int start = i - k;//窗口前一个下标
                if (start >= 0) heap.remove(nums[start]);
                heap.offer(nums[i]);
                if (heap.size() == k) res[i - k + 1] = heap.peek();
            }
            return res;
        }
    }
}
