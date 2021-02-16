package com.david.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindWords_212 {
    public static void main(String[] args) {
        String[] words = {"oath", "pea", "eat", "rain"};
//        String[] words = {"eat"};
        char[][] board = new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        System.out.println(new Solution().findWords(board, words));
    }

    /**
     * 官方题解写法
     */
    static class Solution1 {
        public List<String> findWords(char[][] board, String[] words) {
            return null;
        }
    }


    /**
     * https://leetcode-cn.com/problems/word-search-ii/solution/java-dfs-trie-luo-ji-qing-xi-by-ryancen/
     * trie tree+dfs+回溯
     * time:O(4*m*n)
     * space:O(1)
     */
    static class Solution {
        //四通图方向
        private int[] dx = {1, -1, 0, 0};
        private int[] dy = {0, 0, 1, -1};
        Set<String> res = new HashSet<>();

        public List<String> findWords(char[][] board, String[] words) {
            //1.words放入trie tree
            Trie root = new Trie();
            for (String word : words) {
                root.insert(word);
            }
            //2.dfs board
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    dfs(board, i, j, root);
                }
            }
            return new ArrayList<>(res);
        }

        private void dfs(char[][] board, int i, int j, Trie root) {
            //terminator
            if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) return;//超过边界了
            if (board[i][j] == '@') return;//@证明搜过了
            //process
            root = Trie.search(root, board[i][j]);
            if (root == null) return;
            char c = board[i][j];
            if (root.isEnd) res.add(root.word);
            board[i][j] = '@';
            //drill down
            for (int d = 0; d < dx.length; d++) {
                dfs(board, i + dx[d], j + dy[d], root);
            }
            //revert states
            board[i][j] = c;
        }

        //208的trie tree
        public static class Trie {
            private Trie[] children;
            private boolean isEnd;
            private String word;//存储完整字符串 只有isEnd的节点才会存储

            public Trie() {
                children = new Trie[26];
                isEnd = false;
            }

            public void insert(String word) {
                if (word == null || word.length() == 0) return;
                Trie root = this;
                for (char c : word.toCharArray()) {
                    int index = c - 'a';
                    if (root.children[index] == null) root.children[index] = new Trie();
                    root = root.children[index];
                }
                root.word = word;
                root.isEnd = true;
            }

            public static Trie search(Trie root, char c) {
                int index = c - 'a';
                return root.children[index];
            }
        }
    }

}
