package com.david.leetcode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *  
 * <p>
 * 提示：
 * <p>
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TopKFrequent_347 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3, 5, 6};
        int[] topk = new Solution().topKFrequent(nums, 2);
        System.out.println(Arrays.toString(topk));
    }

    /**
     * 使用大顶堆解决
     * time: O(logn) 堆的插入时间复杂度
     * space: O(n) 开辟堆空间
     */
    static class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            //1.统计每个词出现的频次
//            Map<Integer, Long> freq = Arrays.stream(nums).boxed().collect(Collectors.groupingBy(i -> i, Collectors.counting())); //使用stream变慢了？？
            Map<Integer, Long> freq = new HashMap<>();
            for (int num : nums) {
                freq.compute(num, (key, value) -> value == null ? 0L : value + 1);
            }
            //2.放入大顶堆
            Comparator<Map.Entry<Integer, Long>> comparator = Comparator.<Map.Entry<Integer, Long>>comparingLong(Map.Entry::getValue).reversed();//从大到小
//            Comparator<Map.Entry<Integer, Long>> comparator = (a, b) -> Math.toIntExact(b.getValue() - a.getValue());//从大到小
            PriorityQueue<Map.Entry<Integer, Long>> maxHeap = new PriorityQueue<>(comparator);
            maxHeap.addAll(freq.entrySet());
            //3.取出前n个
            int[] res = new int[k];
            for (int i = 0; i < k; i++) {
                res[i] = maxHeap.poll().getKey();
            }
            return res;
        }
    }
}
