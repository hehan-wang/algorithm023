package com.david.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * https://leetcode-cn.com/problems/word-ladder/solution/suan-fa-shi-xian-he-you-hua-javashuang-xiang-bfs23/
 * TODO 跟433很像 区别在哪？
 */
public class LadderLength_127 {
    public static void main(String[] args) {
        int i = new Solution0().ladderLength("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog"));
        System.out.println(i);
    }


    /**
     * 双向bfs 超
     * 思路
     * 1.使用两个set 从两个方向搜索 。每次把beginSet替换成短的，endSet 存另一端
     * 2.替换当前每个字符 a-z 代替world list 优化world list太长的时候
     * 复杂度分析：
     * l:单词平均长度
     * time:O(26*l*n)
     * space:O(n)
     */
    static class Solution0 {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (!wordList.contains(endWord)) return 0;//都不包含endword 返回0
            int count = 1;
            HashSet<String> beginSet = new HashSet<>(),
                    endSet = new HashSet(),
                    visited = new HashSet<>(),
                    wordSet = new HashSet<>(wordList);
            beginSet.add(beginWord);
            endSet.add(endWord);
            while (!beginSet.isEmpty() && !endSet.isEmpty()) {
                if (beginSet.size() > endSet.size()) {//长度短的放在前
                    HashSet<String> tmp = beginSet;
                    beginSet = endSet;
                    endSet = tmp;
                }
                HashSet<String> newBeginSet = new HashSet<>();
                for (String curr : beginSet) {//搜索beginSet
                    char[] chars = curr.toCharArray();
                    for (int i = 0; i < chars.length; i++) {
                        char origin = chars[i];
                        for (char c = 'a'; c <= 'z'; c++) {
                            chars[i] = c;
                            String newStr = String.valueOf(chars);
                            if (endSet.contains(newStr)) return count + 1;//相遇找到了
                            if (!visited.contains(newStr) && wordSet.contains(newStr)) {
                                visited.add(newStr);
                                newBeginSet.add(newStr);
                            }
                        }
                        chars[i] = origin;
                    }
                }
                beginSet = newBeginSet;
                count++;
            }
            return 0;
        }
    }

    /**
     * 双向bfs 19ms
     */
    static class Solution1 {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (!wordList.contains(endWord)) return 0;
            HashSet<String> wordSet = new HashSet<>(wordList);

            Queue<String> beginQueue = new LinkedList<>();//前队列
            HashSet<String> beginSet = new HashSet<>();
            beginQueue.offer(beginWord);
            beginSet.add(beginWord);

            Queue<String> endQueue = new LinkedList<>();//后队列
            HashSet<String> endSet = new HashSet<>();
            endSet.add(endWord);
            endQueue.offer(endWord);

            int count = 1;//因为头尾都放元素了
            while (!beginQueue.isEmpty() && !endQueue.isEmpty()) {
                count++;
                if (beginQueue.size() > endQueue.size()) {//短的队列放在前面 搜索短的队列
                    Queue<String> tmpQueue = beginQueue;//交换queue
                    beginQueue = endQueue;
                    endQueue = tmpQueue;

                    HashSet<String> tmpSet = beginSet;//交换set
                    beginSet = endSet;
                    endSet = tmpSet;
                }
                int size = beginQueue.size();
                while (size-- > 0) {
                    String curr = beginQueue.poll();
                    char[] chars = curr.toCharArray();
                    for (int i = 0; i < chars.length; i++) {
                        char oldChar = chars[i];
                        for (char j = 'a'; j <= 'z'; j++) {
                            chars[i] = j;
                            String newStr = new String(chars);
                            if (beginSet.contains(newStr)) continue;//访问过跳过
                            if (!wordSet.contains(newStr)) continue;//字典没有跳过
                            if (endSet.contains(newStr)) return count;//在endSet中存在的话证明双向找到了
                            beginSet.add(newStr);
                            beginQueue.add(newStr);
                        }
                        chars[i] = oldChar;
                    }
                }
            }
            return 0;
        }
    }
}
