package com.david.leetcode;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:  
 * <p>
 * 1 是丑数。
 * n 不超过1690。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/chou-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class nthUglyNumber_264 {

    public static void main(String[] args) {
        System.out.println(new Solution().nthUglyNumber(10));
        System.out.println(new Solution().nthUglyNumber(1407));
    }

    /**
     * 使用最大堆计算丑数
     */
    static class Solution {
        Long[] ugly = new Long[1690];
        Integer[] prime = new Integer[]{2, 3, 5};
        PriorityQueue<Long> minHeap = new PriorityQueue<>();//最x小堆
        Set<Long> seen = new HashSet<>();//缓存

        //计算丑数
        {
            //添加第一个元素
            minHeap.add(1L);
            seen.add(1L);
            for (int i = 0; i < 1690; i++) {
                Long currUgly = minHeap.poll();//获取当前最小丑数存起来
                ugly[i] = currUgly;
                //计算下个丑数
                for (int j = 0; j < prime.length; j++) {
                    Long newUgly = currUgly * prime[j];
                    if (!seen.contains(newUgly)) {
                        seen.add(newUgly);
                        minHeap.add(newUgly);
                    }
                }
            }
        }

        public int nthUglyNumber(int n) {
            return Math.toIntExact(ugly[n - 1]);
        }
    }
}
