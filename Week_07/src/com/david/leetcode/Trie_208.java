package com.david.leetcode;

class Trie {
    private boolean isEnd;//表示是否是叶子节点
    private Trie[] next;//下标表示26个字母

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        isEnd = false;
        next = new Trie[26];
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        Trie curr = this;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (curr.next[index] == null) curr.next[index] = new Trie();
            curr = curr.next[index];//迭代到下一层
        }
        curr.isEnd = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {//完全匹配要求匹配到尾结点
        Trie trie = searchPrefix(word);
        return trie != null && trie.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {//前缀匹配 匹配到就可以
        return searchPrefix(prefix) != null;
    }

    public Trie searchPrefix(String word) {
        if (word == null || word.length() == 0) return null;
        Trie curr = this;
        for (char c : word.toCharArray()) {
            curr = curr.next[c - 'a'];
            if (curr == null) return null;
        }
        return curr;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));// 返回 true
        System.out.println(trie.search("app"));// 返回 false
        System.out.println(trie.startsWith("app"));// 返回 true
        trie.insert("app");
        System.out.println(trie.search("app"));// 返回 true
        System.out.println("---end---");
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
