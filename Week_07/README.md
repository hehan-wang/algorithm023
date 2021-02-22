# 学习笔记

## Trie Tree字典树

1. 使用场景

   一般用在字符串查找

2. 代码模板

```java
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
}
```

2. 212单词搜索

   1. 使用Trie+dfs+回溯的时间复杂度分析O(4 x m x n x 3^(l-1) )

      m: board.length

      n: board[0].length

      l: 单词的最大长度

      由于遍历二维数组board(mxn) 加上每个字符要查上下左右四方向 x4 然后在向其余三个方向dfs 单词长度l

## Disjoint Set 并查集

1. 使用场景
   1. 并查集是一种数据结构
   2. 并查集这三个字，一个字代表一个意思。
   3. 并（Union），代表合并
   4. 查（Find），代表查找
   5. 集（Set），代表这是一个以字典为基础的数据结构，它的基本功能是合并集合中的元素，查找集合中的元素
   6. 并查集的典型应用是有关连通分量的问题
   7. 并查集解决单个问题（添加，合并，查找）的时间复杂度都是O(1)O(1)
      因此，并查集可以应用到在线算法中

2. 代码模板

   ```java
   class UnionFind1 {
       private Map<Integer, Integer> father;
   
       public UnionFind1() {
           father = new HashMap<>();
       }
   
       public void add(int x) {
           if (!father.containsKey(x)) {
               father.put(x, null);
           }
       }
   
       public void merge(int x, int y) {
           int rootX = find(x);
           int rootY = find(y);
           if (rootX != rootY) {
               father.put(rootX, rootY);
           }
       }
   
       public int find(int x) {
           int root = x;
           //找到跟节点
           while (father.get(root) != null) {
               root = father.get(root);
           }
           while (x != root) {//路径压缩
               int originalFather = father.get(x);
               father.put(x, root);
               x = originalFather;
           }
           return root;
       }
   
       public boolean isConnected(int x, int y) {
           return find(x) == find(y);
       }
   }
   ```

   

