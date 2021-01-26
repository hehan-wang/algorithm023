package com.david.leetcode;

import java.util.*;

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
//        int i = new Solution().ladderLength("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog"));
        int i = new Solution2().ladderLength("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog"));
        System.out.println(i);
    }

    /**
     * 使用遍历字母代替遍历wordlist 当wordlis大于26的时候遍历的次数会变少
     * 103ms
     */
    static class Solution2 {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            Queue<String> queue = new LinkedList<>();
            HashSet<String> set = new HashSet<>(wordList);
            HashSet<String> visited = new HashSet<>();
            queue.add(beginWord);
            int count = 0;
            while (!queue.isEmpty()) {
                count++;
                int size = queue.size();
                while (size-- > 0) {
                    String curr = queue.poll();
                    char[] chars = curr.toCharArray();
                    for (int i = 0; i < chars.length; i++) {
                        char oldChar = chars[i];
                        for (char j = 'a'; j <= 'z'; j++) {
                            chars[i] = j;
                            String newStr = new String(chars);
                            if (visited.contains(newStr)) continue;
                            if (!set.contains(newStr)) continue;
                            if (endWord.equals(newStr)) return count + 1;
                            visited.add(newStr);
                            queue.offer(newStr);
                        }
                        chars[i] = oldChar;
                    }
                }
            }
            return 0;
        }
    }


    /**
     * 单向bfs 660ms
     */
    static class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            Queue<String> queue = new LinkedList<>();//存bfs层级数据
            boolean[] visited = new boolean[wordList.size()];//存访问过的下标
            queue.offer(beginWord);//入队第一个元素
            int idx = wordList.indexOf(beginWord);
            if (idx > 0) visited[idx] = true;
            int count = 0;
            while (!queue.isEmpty()) {
                count++;//bfs水波纹每一层+1
                int size = queue.size();//bfs当前层个数
                while (size-- > 0) {//一定要size--先使用 后-1
                    String curr = queue.poll();
                    for (int i = 0; i < wordList.size(); i++) {//遍历字典
                        if (visited[i]) continue;//访问过返回跳过
                        String word = wordList.get(i);
                        if (!diff1(word, curr)) continue;//不是差一个字符跳过
                        if (endWord.equals(word)) return count + 1;//curr跟word差一个字符且word就是end 直接返回
                        visited[i] = true;//当前字典访问过
                        queue.offer(word);//搜索下一层
                    }
                }
            }
            return 0;
        }

        //查有没有只有一个字符不同
        private boolean diff1(String s, String curr) {
            int diff = 0;
            for (int i = 0; i < s.length(); i++)
                if (s.charAt(i) != curr.charAt(i) && ++diff > 1) break;
            return diff == 1;
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
