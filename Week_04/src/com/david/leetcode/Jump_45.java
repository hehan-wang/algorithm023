package com.david.leetcode;

public class Jump_45 {
    public static void main(String[] args) {
        System.out.println(new Solution1().jump(new int[]{2, 3, 1, 1, 4}));
    }

    /**
     * 贪心算法  顺藤摸瓜
     * 每次跳到最远的 下标走到最大位置更新下个最大位置 步数+1
     */
    static class Solution1 {
        public int jump(int[] nums) {
            int steps = 0;//存结果步数
            int endIndex = 0;//存每一次跳跃区间的右边界
            int maxIndex = 0;//存当前能跳到的最远位置
            for (int i = 0; i < nums.length - 1; i++) {//减1防止border=length-1这种情况会进入if(i == border) 多加1
                maxIndex = Math.max(maxIndex, i + nums[i]);//取当前能跳到的最大下标
                if (i == endIndex) {//在每一个跳跃区间内选个最大的
                    endIndex = maxIndex;
                    steps++;
                }
            }
            return steps;
        }
    }

}
