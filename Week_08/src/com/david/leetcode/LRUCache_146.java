package com.david.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LRUCache_146 {

    /**
     * 使用HashMap和双向链表实现
     */
    class LRUCache {

        DLinkedNode head, tail;
        Map<Integer, DLinkedNode> cache;
        int capacity;
        int size = 0;


        public LRUCache(int capacity) {
            cache = new HashMap<>();
            this.capacity = capacity;
            head = new DLinkedNode();
            tail = new DLinkedNode();
            head.next = tail;
            tail.prev = head;
        }

        /**
         * 当key 在map存在node放入头部 不存在返回-1
         */
        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) return -1;
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                node = new DLinkedNode(key, value);
                cache.put(key, node);
                addToHead(node);
                //判断是否需要lru 淘汰
                if (++size > capacity) {
                    DLinkedNode tail = removeTail();
                    cache.remove(tail.key);
                    size--;
                }
            } else {
                node.value = value;
                moveToHead(node);
            }
        }

        public DLinkedNode removeTail() {
            DLinkedNode prev = tail.prev;
            remove(prev);
            return prev;
        }

        public void remove(DLinkedNode node) {
            DLinkedNode next = node.next;
            DLinkedNode prev = node.prev;
            prev.next = next;
            next.prev = prev;
        }

        public void addToHead(DLinkedNode node) {
            DLinkedNode next = head.next;
            node.next = next;
            node.prev = head;
            head.next = node;
            next.prev = node;
        }

        public void moveToHead(DLinkedNode node) {
            remove(node);
            addToHead(node);
        }

        public class DLinkedNode {
            private int key;
            private int value;
            private DLinkedNode prev;
            private DLinkedNode next;

            public DLinkedNode() {
            }

            public DLinkedNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }

    //使用LinkedHashMap实现
//    class LRUCache extends LinkedHashMap<Integer, Integer> {
//        int capacity;
//
//        public LRUCache(int capacity) {
//            super(capacity, 0.75f, true);
//            this.capacity = capacity;
//        }
//
//        public int get(int key) {
//            return super.getOrDefault(key, -1);
//        }
//
//        public void put(int key, int value) {
//            super.put(key, value);
//        }
//
//        @Override
//        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
//            return size() > capacity;
//        }
//    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
