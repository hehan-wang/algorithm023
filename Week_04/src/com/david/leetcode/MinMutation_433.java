package com.david.leetcode;

import java.io.Serializable;
import java.util.*;

public class MinMutation_433 {
    public static void main(String[] args) {
//        int i = new Solution().minMutation("AAAACCCC", "CCCCCCCC", new String[]{"AAAACCCA", "AAACCCCA", "AACCCCCA", "AACCCCCC", "ACCCCCCC", "CCCCCCCC", "AAACCCCC", "AACCCCCC"});
        int i = new Solution1().minMutation("AAAACCCC", "CCCCCCCC", new String[]{"AAAACCCA", "AAACCCCA", "AACCCCCA", "AACCCCCC", "ACCCCCCC", "CCCCCCCC", "AAACCCCC", "AACCCCCC"});
        System.out.println(i);
    }

    /**
     * bfs 一般使用队列
     * 1.判断边界条件如果bank没有end直接返回-1
     * 2.start 入队 然后遍历'A', 'C', 'G', 'T'
     * 2.1 找到end直接返回层数
     * 2.2 基因库中有的话去掉删掉元素把那个串入队
     * 2.3 遍历后复原原原字符串 因为每次只能改变一个字母
     */
    static class Solution {
        public int minMutation(String start, String end, String[] bank) {
            HashSet<String> set = new HashSet<>(Arrays.asList(bank));
            if (!set.contains(end)) return -1;//基因库中没有end直接返回-1
            //使用map 反而慢了
//            Map<Character, List<Character>> prime = Map.of(
//                    'A', List.of('C', 'G', 'T'),
//                    'C', List.of('A', 'G', 'T'),
//                    'G', List.of('A', 'C', 'T'),
//                    'T', List.of('A', 'C', 'G')
//            );
            char[] prime = new char[]{'A', 'C', 'G', 'T'};
            Queue<String> queue = new LinkedList<>();
            queue.offer(start);
            set.remove(start);
            int step = 0;
            while (!queue.isEmpty()) {
                step++;
//                for (int count = 0; count < queue.size(); count++) { //TODO 为什么这么遍历就不行？
                for (int count = queue.size(); count > 0; count--) {
                    char[] tmp = queue.poll().toCharArray();
                    for (int i = 0; i < tmp.length; i++) {//遍历字符串
                        char oldChar = tmp[i];
                        for (char p : prime) {//每次替换一个字母
                            tmp[i] = p;
                            String newTmp = String.valueOf(tmp);
                            if (end.equals(newTmp)) {
                                return step;
                            } else if (set.contains(newTmp)) {
                                set.remove(newTmp);
                                queue.offer(newTmp);
                            }
                        }
                        tmp[i] = oldChar;//还原之前的串
                    }
                }
            }
            return -1;
        }
    }

    /**
     * dfs 回溯
     * 遍历基因库找到跟start差一个字母的dfs 放在stepSet中 然后steCount去最小值
     */
    static class Solution1 {
        int minStepCount = Integer.MAX_VALUE;

        public int minMutation(String start, String end, String[] bank) {
            dfs(new HashSet<>(), 0, start, end, bank);
            return minStepCount == Integer.MAX_VALUE ? -1 : minStepCount;
        }

        private void dfs(HashSet<String> stepSet, Integer stepCount, String curr, String end, String[] bank) {
            if (end.equals(curr)) {
                minStepCount = Math.min(stepCount, minStepCount);
                return;
            }
            for (String str : bank) { //遍历bank 找差一个字符的
                int diff = 0;
                for (int i = 0; i < str.length(); i++)
                    if (curr.charAt(i) != str.charAt(i) && ++diff > 1) break;
                if (diff == 1 && !stepSet.contains(str)) {//找到了只差一个字符的
                    stepSet.add(str);
                    dfs(stepSet, stepCount + 1, str, end, bank);
                    stepSet.remove(str);
                }
            }
        }
    }

}
