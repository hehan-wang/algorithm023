package com.david.leetcode;

import java.util.Collections;
import java.util.List;

class NTreeNode {
    public int val;
    public List<NTreeNode> children = Collections.emptyList();

    public NTreeNode() {
    }

    public NTreeNode(int _val) {
        val = _val;
    }

    public NTreeNode(int _val, List<NTreeNode> _children) {
        val = _val;
        children = _children;
    }
}
