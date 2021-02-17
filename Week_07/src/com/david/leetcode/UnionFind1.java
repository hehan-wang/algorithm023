package com.david.leetcode;

import java.util.HashMap;
import java.util.Map;

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

