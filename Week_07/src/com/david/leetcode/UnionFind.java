package com.david.leetcode;

public class UnionFind {
    private int count;
    private int[] parent;

    public UnionFind(int n) {
        this.count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int p) {//到顶了
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];//爷爷赋给爸爸
            p = parent[p];//一次迭代两个
        }
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);//找到p的根
        int rootQ = find(q);//找到q的根
        if (rootP == rootQ) return;//如果在相等则为同一个树
        parent[rootP] = rootQ;//否则一个合道另一个下面
        count--;
    }
}
