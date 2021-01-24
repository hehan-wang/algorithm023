package com.david.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LadderLength_127 {
    public static void main(String[] args) {
        int i = new Solution().ladderLength("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog"));
        System.out.println(i);
    }

    /**
     * TODO bfs 目前答案不对
     */
    static class Solution {
        int minStep = Integer.MAX_VALUE;

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            dfs(new HashSet<>(), 1, beginWord, endWord, wordList);
            return minStep == Integer.MAX_VALUE ? -1 : minStep;
        }

        private void dfs(Set<String> stepSet, int stepCount, String curr, String endWord, List<String> wordList) {
            //terminator
            if (endWord.equals(curr)) {
                minStep = Math.min(stepCount, minStep);
                return;
            }
            //process
            for (String word : wordList) {
                int diff = 0;
                for (int i = 0; i < word.length(); i++)
                    if (curr.charAt(i) != word.charAt(i) && ++diff > 1) break;
                if (diff == 1 && !stepSet.contains(word)) {
                    stepSet.add(word);
                    //drill down
                    dfs(stepSet, stepCount + 1, word, endWord, wordList);
                    //revert states
                    stepSet.remove(word);
                }

            }
        }
    }
}
